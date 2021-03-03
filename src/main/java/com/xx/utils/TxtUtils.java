package com.xx.utils;

import com.xx.enums.ExceptionEnum;
import com.xx.exceptions.XXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiexing
 * @discription txt工具类
 * @date 2020/3/25
 */
public class TxtUtils {

    private static final Logger logger = LoggerFactory.getLogger(TxtUtils.class);

    public static List<String> TABLES = null;

    static {
        if (TABLES == null) {
            TABLES = new ArrayList<String>();
        }
    }

    public static void readTables(){
        readTables(System.getProperty("user.dir") + "\\src\\main\\resources\\tables.txt");
    }

    public static void readTables(String targetUrl) {
        try {
            File file = new File(targetUrl);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String tableName = "";
            while (StringUtils.isNotEmptyStr((tableName = reader.readLine()))) {
                TABLES.add(tableName);
            }
        } catch (Exception e) {
            logger.error("读取表名出错,原因为{}",e.getMessage(),e);
            throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),e.getMessage());
        }
    }
}
