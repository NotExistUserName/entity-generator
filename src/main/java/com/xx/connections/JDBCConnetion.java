package com.xx.connections;

import com.xx.consts.GeneratorConsts;
import com.xx.enums.ExceptionEnum;
import com.xx.exceptions.XXException;
import com.xx.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author xiexing
 * @discription jdbc连接
 * @date 2020/3/25
 */
public class JDBCConnetion {

    private static final Logger logger = LoggerFactory.getLogger(JDBCConnetion.class);
    /**
     * 驱动
     */
    private static final String JDBC_DRIVER_CLASS = PropertiesUtils.propertiesMap.get(GeneratorConsts.JDBC_DRIVER_CLASS);

    /**
     * 连接地址
     */
    private static final String JDBC_URL = PropertiesUtils.propertiesMap.get(GeneratorConsts.JDBC_URL);

    /**
     * 用户名称
     */
    private static final String JDBC_USER = PropertiesUtils.propertiesMap.get(GeneratorConsts.JDBC_USER);

    /**
     * 密码
     */
    private static final String JDBC_PASSWORD = PropertiesUtils.propertiesMap.get(GeneratorConsts.JDBC_PASSWORD);

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnetion(){
        try {
            //加载驱动
            Class.forName(JDBC_DRIVER_CLASS);
            //获取连接
            return DriverManager.getConnection(JDBC_URL,buildJdbcProps());
        } catch (Exception e) {
            logger.error("获取连接失败",e);
            throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),"获取连接失败");
        }
    }

    /**
     * 构建连接数据库的参数
     * @return
     */
    private static Properties buildJdbcProps() {
        Properties props = new Properties();
        //设置允许获取备注
        props.put("remarksReporting","true");
        props.put("user",JDBC_USER);
        props.put("password",JDBC_PASSWORD);
        return props;
    }

    /**
     * 关闭连接
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            logger.error("释放连接失败",e);
            throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),"释放连接失败");
        }
    }
}
