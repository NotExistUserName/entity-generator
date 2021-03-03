package com.xx;

import com.xx.connections.JDBCConnetion;
import com.xx.utils.EntityGeneratorUtils;
import com.xx.utils.PropertiesUtils;
import com.xx.utils.TxtUtils;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author xiexing
 * @discription 实体类生成执行器
 * @date 2020/3/24
 */
public class EntityGeneratorBootstrap {

    private static Connection CONNECTION = null;

    @Before
    public void before() throws Exception{
        PropertiesUtils.readProperties();
        TxtUtils.readTables();
        CONNECTION = JDBCConnetion.getConnetion();
    }

    @Test
    public void genEntity() {
        EntityGeneratorUtils.gen(TxtUtils.TABLES,CONNECTION);
    }
}
