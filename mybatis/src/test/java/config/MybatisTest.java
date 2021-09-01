package config;

import co.kr.training.dto.AreaSaleVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void setUp() throws Exception{
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


//    @Ignore
    @Test
    public void selectTestOne() throws IOException {
        try(SqlSession session = sqlSessionFactory.openSession()){
            AreaSaleVO areaSaleVO = session.selectOne("SelectBaseMapper.findAreaSales", "10");
            logger.info("Sale cnt : {} ", areaSaleVO);
        }

    }




}
