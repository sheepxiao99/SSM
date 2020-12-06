import com.ssm.dao.IAccountdao;
import com.ssm.domain.Account;
import com.ssm.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestSSM {


    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService bean = ac.getBean(AccountService.class);
        bean.findAll();
    }

    @Test
    public void run2() throws IOException {
        Account account = new Account("奥利给",100d);

        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //创建SQLSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建SQLSession对象
        SqlSession sqlSession = factory.openSession();

        //获取到接口的代理对象
        IAccountdao mapper = sqlSession.getMapper(IAccountdao.class);
        mapper.saveAccount(account);
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();

    }

    @Test
    public void run3() throws IOException {
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //创建SQLSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建SQLSession对象
        SqlSession sqlSession = factory.openSession();

        //获取到接口的代理对象
        IAccountdao mapper = sqlSession.getMapper(IAccountdao.class);

        List<Account> all = mapper.findAll();
        System.out.println(all);

        sqlSession.close();
        inputStream.close();
    }
}
