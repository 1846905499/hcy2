package com.city.hcy.config;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.alibaba.druid.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Component
@Slf4j
public class LogUtils {
    /**
     * 默认记录操作日志的DAO层方法名开头
     */
    public static final String[] DEFAULT_RECORD_METHOD_START = {"insert", "update", "delete", "remove"};

    /**
     * 默认不记录的操作方法（记录日志的方法）
     */
    public static final String[] DEFAULT_NOT_RECORED_METHOD = new String[]{"com.city.hcy.mapper.LogMapper.insertLog"};

    private static LogUtils logUtils;

    /**
     * 注入SqlSessionFactory对象
     */
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 注入DataSource对象
     */
    @Autowired
    private DataSource mysqlDataSource;

    private LogUtils() {
    }

    /**
     * 给logUtils对象赋值
     */
    @PostConstruct
    public void init() {
        logUtils = this;
        logUtils.sqlSessionFactory = this.sqlSessionFactory;
        logUtils.mysqlDataSource = this.mysqlDataSource;
    }

    /**
     * 判断方法名是否满足日志记录格式
     *
     * @param methodName
     * @return
     */
    public static boolean verifyMethodName(String methodName) {
        boolean methodNameFlag = false;
        for (int i = 0; i < DEFAULT_RECORD_METHOD_START.length; i++) {
            if (methodName.startsWith(DEFAULT_RECORD_METHOD_START[i])) {
                methodNameFlag = true;
                break;
            }
        }
        return methodNameFlag;
    }

    /**
     * 验证方法是否需要日志记录
     *
     * @param methodFullName
     * @return
     */
    public static Map<String, Object> verifyRecordLog(String methodFullName) {
        Map<String, Object> resultMap = new HashMap<>();

        for (int i = 0; i < DEFAULT_NOT_RECORED_METHOD.length; i++) {
            if (methodFullName.equals(DEFAULT_NOT_RECORED_METHOD[i])) {
                return resultMap;
            }
        }

        boolean isRecord = false;
        String desc = StringUtils.EMPTY;
        int flag = methodFullName.lastIndexOf(".");
        String classPath = methodFullName.substring(0, flag);
        String methodName = methodFullName.substring(flag + 1);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("判断是否需要记录日志异常!", e);
        }
        if (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (methodName.equals(method.getName())) { // 找到当前方法
                    RecordLog rl = method.getAnnotation(RecordLog.class); // 判断是否有RecordLog注解
                    if (rl != null) { // 有RecordLog注解，直接进行记录
                        isRecord = true;
                        desc = rl.desc();
                    } else {
                        // 没有UnRecordLog注解，并且方法满足记录格式则进行记录
                        if (method.getAnnotation(UnRecordLog.class) == null && verifyMethodName(methodName)) {
                            isRecord = true;
                        }
                    }
                    break;
                }
            }
        }
        resultMap.put("isRecord", isRecord); // 是否记录
        resultMap.put("desc", desc); // 方法描述
        return resultMap;
    }

    /**
     * 填充日记记录SQL参数
     *
     * @param methodFullName
     * @param desc
     * @param originalSql
     * @return
     */
    private static List<Object> getParamList(String methodFullName, String desc, String originalSql) {
        List<Object> paramList = new ArrayList<>();

        String unknownFlag = "UNKNOWN";
        // 获取Request对象
        HttpServletRequest request = GlobalWebVarUtil.getRequest();

        String uri;
        String ip;

        if (request == null) {
            uri = unknownFlag;
            ip = unknownFlag;
        } else {
            uri = request.getRequestURI();
            ip = IPUtils.getIpAddress(request);
        }
        // id
        paramList.add(System.currentTimeMillis());
        // 调用的接口
        paramList.add(uri);
        //DAO层执行的方法名称
        paramList.add(methodFullName);
        // ip地址
        paramList.add(ip);
        // 完整SQL语句
        paramList.add(handlerSql(originalSql));
        // 描述
        paramList.add(desc);
        // 创建时间
        paramList.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return paramList;
    }

    /**
     * 处理SQL语句
     *
     * @param originalSql
     * @return
     */
    private static String handlerSql(String originalSql) {
        String sql = originalSql.substring(originalSql.indexOf(":") + 1);
        // 将原始sql中的空白字符（\s包括换行符，制表符，空格符）替换为" "
        return sql.replaceAll("[\\s]+", " ");
    }

    /**
     * 获取日志保存SQL
     *
     * @param methodFullName
     * @param desc
     * @param originalSql
     * @return
     */
    public static String getSaveLogSql(String methodFullName, String desc, String originalSql) {
        String sql = logUtils.sqlSessionFactory.getConfiguration()
                .getMappedStatement(DEFAULT_NOT_RECORED_METHOD[0]).getBoundSql(null).getSql();
        List<Object> paramList = getParamList(methodFullName, desc, originalSql);
        sql = paramList != null && !paramList.isEmpty() ? SQLUtils.format(sql, JdbcConstants.MYSQL, paramList) : null;
        return sql;
    }

    /**
     * 获取mysql Connection对象
     *
     * @return
     */
    public static Connection getMysqlConnection() {
        Connection conn = null;
        try {
            conn = logUtils.mysqlDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("保存日志时获取Connection对象异常!", e);
        }

        return conn;
    }

}