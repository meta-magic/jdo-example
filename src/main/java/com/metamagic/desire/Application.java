package com.metamagic.desire;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jdo.JdoTransactionManager;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	@Bean
	public PersistenceManagerFactory persistenceManagerFactory(){
		return JDOHelper.getPersistenceManagerFactory("PersistenceUnit");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
