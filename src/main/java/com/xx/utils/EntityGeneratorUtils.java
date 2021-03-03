package com.xx.utils;

import com.xx.asserts.XXAssert;
import com.xx.consts.DBDataTypeConsts;
import com.xx.consts.GeneratorConsts;
import com.xx.enums.ExceptionEnum;
import com.xx.exceptions.XXException;
import com.xx.vo.ColumnVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author xiexing
 * @discription 实体类生成器工具类
 * @date 2020/3/30
 */
public class EntityGeneratorUtils {

    private static final Logger logger = LoggerFactory.getLogger(EntityGeneratorUtils.class);

    /**
     * 实体类生成存放路径
     */
    public static final String MODEL_TARGET_URL = PropertiesUtils.propertiesMap.get(GeneratorConsts.MODEL_TARGET_URL);

    /**
     *  实体类生成包名
     */
    public static final String PACKAGE_NAME = PropertiesUtils.propertiesMap.get(GeneratorConsts.PACKAGE_NAME);

    /**
     * 实体类生成作者
     */
    public static final String AUTHOR_NAME = PropertiesUtils.propertiesMap.get(GeneratorConsts.AUTHOR_NAME);

    /**
     * 根据表名生成对应的实体类
     * @param tableNames 需要生成实体类的表名
     * @param connection jdbc连接
     */
    public static void gen(List<String> tableNames, Connection connection){
        XXAssert.isTrue(tableNames != null && !tableNames.isEmpty(),"数据库表不能为空,请在根目录下tables.txt中分行填写");
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
            for (String tableName : tableNames) {
                XXAssert.notEmpty(tableName,"数据库表不能为空,请在根目录下tables.txt中分行填写");
                //原始表名
                String originTableName = tableName;
                tableName = RegexUtils.subUserName(tableName).toUpperCase();
                //根据表名获取类名
                String className = RegexUtils.underlineToCamel(tableName.toLowerCase(),true);
                //获取表备注
                String tableRemark = "";
                ResultSet tableRs = metaData.getTables(null, "%", tableName, new String[] { "TABLE" });
                while (tableRs.next()) {
                    tableRemark = tableRs.getString("REMARKS");
                }
                //获取该表的主键
                Map<String,String> primaryKeyMap = new HashMap<String, String>(2);
                ResultSet primaryKeyRs = metaData.getPrimaryKeys(null,null,tableName);
                while (primaryKeyRs.next()) {
                    primaryKeyMap.put(primaryKeyRs.getString("COLUMN_NAME"),tableName);
                }
                //获取列名以及列备注(表名称必须是大写)
                ResultSet rs = metaData.getColumns(null, "%", tableName, "%");
                List<ColumnVO> columnVOList = new ArrayList<ColumnVO>();
                while (rs.next()) {
                    ColumnVO column = new ColumnVO();
                    //列名
                    String columnName = rs.getString("COLUMN_NAME");
                    column.setColName(columnName);
                    //列备注
                    column.setColRemark(rs.getString("REMARKS"));
                    //是否是主键
                    if (primaryKeyMap.containsKey(columnName)) {
                        column.setPrimaryKey(true);
                    }
                    //数据类型
                    convertDbDataType(column,rs);
                    columnVOList.add(column);
                }
                XXAssert.isTrue(columnVOList != null && !columnVOList.isEmpty(),"表字段不能为空");
                //文件写出
                writeModel(originTableName,className,tableRemark,columnVOList);
            }
            logger.info("实体类生成完毕");
        } catch (Exception e) {
            if (e instanceof SQLException) {
                logger.error("获取数据库表信息出错",e);
                throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),"获取数据库表信息出错");
            }
            if (e instanceof FileNotFoundException || e instanceof IOException) {
                logger.error("文件写出出错",e);
                throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),"文件写出出错");
            }
            throw new XXException(ExceptionEnum.SYSTEM_ERROR.getCode(),ExceptionEnum.SYSTEM_ERROR.getDesc());
        }
    }


    /**
     * 实体类写出
     * @param tableName 表名
     * @param className 类名
     * @param tableRemark 表备注
     * @param columnVOList 字段列表
     */
    public static void writeModel(String tableName,String className,String tableRemark,List<ColumnVO> columnVOList) throws Exception{
        File dir = new File(MODEL_TARGET_URL);
        if (dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(MODEL_TARGET_URL + className + GeneratorConsts.ENTITY_SUFFIX);
        StringBuilder javaFile = new StringBuilder();
        javaFile.append("package ").append(PACKAGE_NAME).append(";").append("\r\n").append("\r\n");
        javaFile.append("import java.util.*;").append("\r\n");
        javaFile.append("import java.math.*;").append("\r\n");
        javaFile.append("import java.io.*;").append("\r\n");
        javaFile.append("import javax.persistence.Table;").append("\r\n");
        javaFile.append("import javax.persistence.Entity;").append("\r\n");
        javaFile.append("import javax.persistence.Column;").append("\r\n");
        javaFile.append("import javax.persistence.GeneratedValue;").append("\r\n");
        javaFile.append("import javax.persistence.Id;").append("\r\n");
        javaFile.append("import org.hibernate.annotations.*;").append("\r\n").append("\r\n");
        javaFile.append("/**").append("\r\n");
        javaFile.append(" * @author ").append(AUTHOR_NAME).append("\r\n");
        javaFile.append(" * @description ").append(tableRemark).append("\r\n");
        javaFile.append(" * @date ").append(DateUtils.format(new Date(),DateUtils.TIME_24_WITH_RUNG)).append("\r\n");
        javaFile.append(" */").append("\r\n");
        javaFile.append("@Entity ").append("\r\n");
        javaFile.append("@Table(name = ").append("\"").append(tableName).append("\"").append(")").append("\r\n");
        javaFile.append("@DynamicUpdate ").append("\r\n");
        javaFile.append("@DynamicInsert ").append("\r\n");
        javaFile.append("public class ").append(className).append(" implements Serializable {").append("\r\n");
        javaFile.append("    private static final long serialVersionUID = 1L; ").append("\r\n").append("\r\n");
        //字段名称与注释开始
        for (ColumnVO column : columnVOList) {
            javaFile.append("    /**").append("\r\n");
            javaFile.append("     * ").append(column.getColRemark()).append("\r\n");
            javaFile.append("     */").append("\r\n");
            javaFile.append("    private ").append(column.getDataType().getSimpleName()).append(" ").append(RegexUtils.underlineToCamel(column.getColName().toLowerCase(),false)).append(";").append("\r\n");
            javaFile.append("\r\n");
        }
        javaFile.append("\r\n");
        //字段setter/getter开始
        for (ColumnVO column : columnVOList) {
            //getter
            if (column.isPrimaryKey()) {
                javaFile.append("    @Id").append("\r\n");
                javaFile.append("    @GeneratedValue(generator = \"system_generator\")").append("\r\n");
                javaFile.append("    @GenericGenerator(name = \"system_generator\"," +
                        "strategy = \"com.jlpay.manage.tools.sequence.impl.JLSequenceGenerator\"," +
                        "parameters = {@org.hibernate.annotations.Parameter(name = \"seqName\",value = \"").append(tableName).append("\")})").append("\r\n");
            }
            String camelColNameForSG = RegexUtils.underlineToCamel(GeneratorConsts.UNDERLINE_BUILD + column.getColName().toLowerCase(),false);
            String camelColNameForReturn = RegexUtils.underlineToCamel(column.getColName().toLowerCase(),false);
            javaFile.append("    @Column(name = \"").append(column.getColName()).append("\")").append("\r\n");
            javaFile.append("    public ").append(column.getDataType().getSimpleName()).append(" ").append(GeneratorConsts.GETTER).append(camelColNameForSG).append("(){").append("\r\n");
            javaFile.append("        return ").append(camelColNameForReturn).append(";").append("\r\n");
            javaFile.append("    }").append("\r\n");
            //setter
            javaFile.append("    public void ").append(GeneratorConsts.SETTER).append(camelColNameForSG).append("(").append(column.getDataType().getSimpleName()).append(" ").append(camelColNameForReturn).append(")").append(" ").append("{").append("\r\n");
            javaFile.append("        this.").append(camelColNameForReturn).append(" = ").append(camelColNameForReturn).append(";").append("\r\n");
            javaFile.append("    }").append("\r\n");
            javaFile.append("\r\n");
        }
        javaFile.append("}");
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(javaFile.toString());
        bufferedWriter.close();
        writer.close();
    }

    /**
     * 转换数据库类型
     * @param column
     * @param rs
     * @throws Exception
     */
    public static void convertDbDataType(ColumnVO column,ResultSet rs) throws Exception{
        String dataType = rs.getString("TYPE_NAME");
        XXAssert.notEmpty(dataType,"获取数据库字段类型出错");
        switch (dataType) {
            case DBDataTypeConsts.DB_DATE:
            case DBDataTypeConsts.DB_DATETIME:
                column.setDataType(Date.class);
                break;
            case DBDataTypeConsts.DB_NUMBER:
            case DBDataTypeConsts.DB_DECIMAL:
                column.setDataType(BigDecimal.class);
                break;
            case DBDataTypeConsts.DB_INTEGER:
            case DBDataTypeConsts.DB_MEDIUMINT:
            case DBDataTypeConsts.DB_SMALLINT:
            case DBDataTypeConsts.DB_TINYINT:
                column.setDataType(Integer.class);
                break;
            case DBDataTypeConsts.DB_FLOAT:
                column.setDataType(Float.class);
                break;
            case DBDataTypeConsts.DB_DOUBLE:
                column.setDataType(Double.class);
                break;
            default:
                column.setDataType(String.class);
                break;
        }
    }
}
