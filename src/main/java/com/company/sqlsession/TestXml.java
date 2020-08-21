package com.company.sqlsession;

import com.company.mapper.UserMapper;
import com.company.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestXml {

    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        try  {
            SqlSession session = sqlSessionFactory.openSession();
            User user = session.selectOne(
                    "com.company.mapper.UserMapper.findUserById", 1);
            List<User> userList = session.selectList("com.company.mapper.UserMapper.findAll");
//            UserMapper mapper = session.getMapper(UserMapper.class);
//            List<User> userList= mapper.findUserById(1);
            System.out.println(user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("444444");
    }
}
