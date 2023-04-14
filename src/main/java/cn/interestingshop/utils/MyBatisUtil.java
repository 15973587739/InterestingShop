package cn.interestingshop.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    static {
        try {
            InputStream is= Resources.getResourceAsStream("mybatis-config.xml");
            factory=new SqlSessionFactoryBuilder().build(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSession createSqlSession(){
        return factory.openSession(false);
    }
    public static void closeSqlSession(SqlSession sqlSession){
        if (sqlSession !=null) {
            sqlSession.close();
        }
    }
}
