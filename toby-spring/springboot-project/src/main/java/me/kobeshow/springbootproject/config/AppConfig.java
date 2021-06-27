package me.kobeshow.springbootproject.config;

import me.kobeshow.springbootproject.proxy.NameMatchClassMethodPointcut;
import me.kobeshow.springbootproject.proxy.TransactionAdvice;
import me.kobeshow.springbootproject.factorybean.Message;
import me.kobeshow.springbootproject.factorybean.MessageFactoryBean;
import me.kobeshow.springbootproject.service.DummyMailSender;
import me.kobeshow.springbootproject.service.TestUserServiceImpl;
import me.kobeshow.springbootproject.service.UserServiceImpl;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mariadb://localhost:3306/springtest");
        dataSource.setUsername("kobeshow");
        dataSource.setPassword("eptest00");

        return dataSource;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public Message message() throws Exception {
        MessageFactoryBean messageFactoryBean = new MessageFactoryBean();
        messageFactoryBean.setText("Factory Bean");
        return messageFactoryBean.getObject();
    }

    @Bean
    public DefaultPointcutAdvisor transactionAdvisor() {
        DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
        pointcutAdvisor.setPointcut(transactionPointcut());
        pointcutAdvisor.setAdvice(transactionAdvice());
        return pointcutAdvisor;
    }

    @Bean
    public TransactionAdvice transactionAdvice() {
        TransactionAdvice advice = new TransactionAdvice();
        advice.setTransactionManager(transactionManager());
        return advice;
    }

    @Bean
    public NameMatchClassMethodPointcut transactionPointcut() {
        NameMatchClassMethodPointcut pointcut = new NameMatchClassMethodPointcut();
        pointcut.setMappedClassName("*serviceImpl");
        pointcut.setMappedName("upgrade*");
        return pointcut;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
}
