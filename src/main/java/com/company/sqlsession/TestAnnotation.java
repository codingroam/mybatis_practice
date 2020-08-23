package com.company.sqlsession;


import com.alibaba.druid.pool.DruidDataSource;
import com.company.mapper.OrderMapper;
import com.company.mapper.UserMapper;
import com.company.po.Order;
import com.company.po.User;
import com.company.utils.InitLogRecord;
import com.sun.xml.internal.bind.v2.util.DataSourceSource;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.logging.log4j2.Log4j2LoggerImpl;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;


public class TestAnnotation {
    public static void main(String[] args) {
       // InitLogRecord.initLog();
        /*
        * 第一种创建数据库链接
        * */
//        Properties properties = new Properties();
//        properties.setProperty("driver","com.mysql.jdbc.Driver");
//        properties.setProperty("url","jdbc:mysql://127.0.0.1:3306/mybatis?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
//        properties.setProperty("username","root");
//        properties.setProperty("password","root");
//        PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
//
//        pooledDataSourceFactory.setProperties(properties);
//        DataSource dataSource = pooledDataSourceFactory.getDataSource();

/*
*
* 第二种通过druid连接池
*
* */
        DataSource dataSource = getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();



        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        //用Log4j打印sql
        configuration.setLogImpl(StdOutImpl.class);

        configuration.addMapper(OrderMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try{
            SqlSession session = sqlSessionFactory.openSession();
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            List<Order> orderList = mapper.findAll();
            System.out.println(orderList.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static DataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mybatis?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return druidDataSource;
    }
}
