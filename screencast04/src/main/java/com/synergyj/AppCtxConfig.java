package com.synergyj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.synergyj.impl.BeanPrototypeImpl;
import com.synergyj.impl.BeanSingletonImpl;
import com.synergyj.impl.BeanUsandoPrototypeAndSingletonImpl;

@Configuration
public class AppCtxConfig {

	@Bean
	@Scope("prototype")
	public BeanPrototype beanPrototype(){
		return new BeanPrototypeImpl();
	}
	
	@Bean
	public BeanSingleton beanSingleton(){
		return new BeanSingletonImpl();
	}
	
	@Bean
	public BeanUsandoPrototypeAndSingleton beanUsandoPrototypeAndSingleton(){
		BeanUsandoPrototypeAndSingletonImpl beanCompuesto = new BeanUsandoPrototypeAndSingletonImpl(){
			public BeanPrototype getBeanPrototype() {
				return beanPrototype();
			}
		};
		beanCompuesto.setBeanSingleton(beanSingleton());
		return beanCompuesto; 
	}
}
