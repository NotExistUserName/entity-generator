package com.xx.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiexing
 * @discription 属性读取工具类
 * @date 2020/3/24
 */
public class PropertiesUtils {

    public static Map<String,String> propertiesMap = null;

    static {
        if (propertiesMap == null) {
            propertiesMap = new HashMap<String, String>();
        }
    }

    public static void readProperties () throws Exception{
        String userDir = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
        readProperties(userDir);
    }

    public static void readProperties (String targetUrl) throws Exception{
        InputStream inputStream = new FileInputStream(targetUrl);
        Properties readProperties = new Properties();
        readProperties.load(inputStream);
        Enumeration enumeration = readProperties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = StringUtils.toString(enumeration.nextElement());
            propertiesMap.put(key,readProperties.getProperty(key));
        }
    }
}
