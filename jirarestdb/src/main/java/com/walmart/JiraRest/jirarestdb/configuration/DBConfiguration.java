package com.walmart.JiraRest.jirarestdb.configuration;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DBConfiguration {
	private static Logger logger = LoggerFactory.getLogger(DBConfiguration.class);
	
	@Autowired
	private Environment env;
	
	@Bean(name = "hiveJdbcDataSource")
	@Qualifier("hiveJdbcDataSource")
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setUrl("jdbc:mysql://dfw-dbblx014a-07.prod.walmart.com:33307/presidio");
		dataSource.setDriverClassName(env.getProperty("hive.driver-class-name"));
		dataSource.setUsername("presidio_mysql_rt");
		dataSource.setPassword("");
		
		logger.debug("Hive DataSource");
		return dataSource;
	}

	@Bean(name = "hiveJdbcTemplate")
	public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveJdbcDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}

