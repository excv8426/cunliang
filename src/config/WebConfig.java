package config;

import java.util.Properties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "javasrc")
@EnableTransactionManagement
@EnableScheduling
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env;
	/**
	 * 配置数据源（tomcat jdbc 连接池）*/
	@Bean
	public DataSource getDataSource(){
		System.out.println("初始化DataSource。");
        PoolProperties p = new PoolProperties();
        p.setUrl(env.getProperty("jdbc.url"));
        p.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        p.setUsername(env.getProperty("jdbc.username"));
        p.setPassword(env.getProperty("jdbc.password"));
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setRemoveAbandoned(true);
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
		return datasource;
	}
	
	/**
	 * 配置spring jdbc模板*/
	@Bean
	public JdbcTemplate getJdbcTemplate(){
		System.out.println("配置JdbcTemplate。");
		JdbcTemplate jdbcTemplate=new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}
	
	/**
	 * 配置hibernate sessionfactory*/
	@Bean
	public LocalSessionFactoryBean getLocalSessionFactoryBean(){
		System.out.println("配置hibernate SessionFactory。");
		Properties properties = new Properties(); 
		properties.put("hibernate.show_sql", false); 
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect"); 
		properties.put("hibernate.format_sql", false);
		/*properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		properties.put("net.sf.ehcache.configurationResourceName", "");
		properties.put("hibernate.cache.use_second_level_cache", true);
		properties.put("hibernate.cache.use_query_cache", true);
		properties.put("hibernate.generate_statistics", true);*/
		LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(getDataSource());
		localSessionFactoryBean.setPackagesToScan("javasrc.entity");
		localSessionFactoryBean.setHibernateProperties(properties);
		return localSessionFactoryBean;
	}
	
	/**
	 * 配置事务管理器
	 * 同时管理spring与hibernate*/
	@Bean
	public HibernateTransactionManager getDataSourceTransactionManager(){
		System.out.println("配置事务管理器。");
		HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager();
		hibernateTransactionManager.setDataSource(getDataSource());
		hibernateTransactionManager.setSessionFactory(getLocalSessionFactoryBean().getObject());
		return hibernateTransactionManager;
	}
	
	/**
	 * 配置文件上传*/
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver(){
		System.out.println("配置上传文件解析器。");
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(209715200);
		return commonsMultipartResolver;
	}
	
}
