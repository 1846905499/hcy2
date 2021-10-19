package com.city.hcy.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
@Slf4j
@Component
public class MybatisLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 执行方法
        Object result = invocation.proceed();

        // 获取MapperStatement对象，获取到sql的详细信息
        Object realTarget = realTarget(invocation.getTarget());
        // 获取metaObject对象
        MetaObject metaObject = SystemMetaObject.forObject(realTarget);
        // 获取MappedStatement对象
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取方法的全类名称
        String methodFullName = ms.getId();

        // 判断是否是需要日志记录的方法
        Map<String, Object> map = LogUtils.verifyRecordLog(methodFullName);
        if (!map.isEmpty() && (boolean) map.get("isRecord")) {

            Statement statement;
            // 获取方法参数
            Object[] args = invocation.getArgs();
            Object firstArg = args[0];
            if (Proxy.isProxyClass(firstArg.getClass())) {
                statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
            } else {
                statement = (Statement) firstArg;
            }

            MetaObject stmtMetaObj = SystemMetaObject.forObject(statement);
            // 获取Statement对象（sql语法已经构建完毕）
            statement = (Statement) stmtMetaObj.getOriginalObject();
            // 获取sql语句
            String originalSql = statement.toString();

            String saveLogSql = LogUtils.getSaveLogSql(methodFullName, (String) map.get("desc"), originalSql);

            if (StringUtils.isNotBlank(saveLogSql)) {
                Connection connection = statement.getConnection();

                if (connection.isReadOnly()) { // 当前事务是只读事务,则重新用不同的Connection对象
                    Connection mysqlConnection = LogUtils.getMysqlConnection();
                    if (mysqlConnection != null) {
                        try {
                            mysqlConnection.createStatement().execute(saveLogSql);
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("拦截器记录日志出错!", e);
                        } finally {
                            mysqlConnection.close();//关闭连接
                        }
                    }
                } else {
                    connection.createStatement().execute(saveLogSql);
                }
            }
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {

    }

    /**
     * <p>
     * 获得真正的处理对象,可能多层代理.
     * </p>
     */
    @SuppressWarnings("unchecked")
    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T) target;
    }
}