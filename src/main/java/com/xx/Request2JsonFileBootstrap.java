package com.xx;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.IOUtils;
import com.xx.timer.TimeTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * @author xiexing
 * @description 将实体类对象转成json文件
 * @date 2020/11/16
 */
@Slf4j
public class Request2JsonFileBootstrap {

    /**
     * 需要生成json文件得实体类
     */
    public static final String SOURCE_CLASS_FILE = "com.xx.request.GenMonthInvoiceRequest";

    /**
     * 生成得json文件存储路径
     */
    public static final String TARGET_JSON_FILE = System.getProperty("user.dir") + "\\src\\main\\java\\com\\xx\\request\\genMonthInvoiceRpc.json";

    /**
     * 命令字名称
     */
    public static final String COMMAND_SERVICE_NAME = "COMMAND_SERVICE_NAME,COMMAND_ID";

    @Test
    public void genJsonFile() {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(TARGET_JSON_FILE);
            Class clazz = Class.forName(SOURCE_CLASS_FILE);
            Field[] declaredFields = clazz.getDeclaredFields();
            if (declaredFields == null || declaredFields.length == 0) {
                return;
            }
            StringBuilder jsonFile = new StringBuilder("{\r\n");
            for (int i = 0; i < declaredFields.length; i++) {
                Field field = declaredFields[i];
                //跳过序列号
                if (Modifier.isFinal(field.getModifiers())) {
                    if (COMMAND_SERVICE_NAME.contains(field.getName())) {
                        jsonFile.append("\t").append("\"command_id\": \"" + field.get(clazz) + "\",").append("\r\n");
                    }
                    continue;
                }
                //如果对象被@JsonFiled修饰则取注解中得值
                if (field.isAnnotationPresent(JSONField.class)) {
                    String fieldName = field.getAnnotation(JSONField.class).name();
                    assert fieldName != null && fieldName.trim().length() > 0 : "属性字段值配置有误";
                    jsonFile.append("\t\"").append(fieldName).append("\": ").append("\"\",").append("\r\n");
                    continue;
                }
                //最后获取字段名称
                String fieldName = field.getName();
                jsonFile.append("\t\"").append(fieldName).append("\": ").append("\"\",").append("\r\n");
            }
            int index = jsonFile.lastIndexOf(",");
            jsonFile = jsonFile.deleteCharAt(index);
            jsonFile.append("}");
            outputStream.write(jsonFile.toString().getBytes());
        } catch (Exception e) {
            log.error("JSON文件写出失败,原因为-->{}",e.getMessage(),e);
            throw new RuntimeException();
        } finally {
            if (outputStream != null) {
                IOUtils.close(outputStream);
            }
        }
        log.info("JSON文件写出成功...");
        Timer timer = new Timer();
        List<String> filePaths = new ArrayList<String>();
        filePaths.add(TARGET_JSON_FILE);
        timer.schedule(new TimeTask(filePaths),5000,10000);//五秒后删除,单位为毫秒
    }
}
