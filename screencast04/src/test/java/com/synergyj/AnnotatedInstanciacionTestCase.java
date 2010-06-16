package com.synergyj;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/AnnotatedAppCtx.xml" })
public class AnnotatedInstanciacionTestCase {

	//@Autowired
	//ApplicationContext appCtx;
	ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppCtxConfig.class);
	
	BeanSingleton singleton1;
	BeanSingleton singleton2;
	BeanPrototype prototype1;
	BeanPrototype prototype2;
	BeanUsandoPrototypeAndSingleton beanCombinado;

	@Before
	public void setUp() {
		singleton1 = (BeanSingleton) appCtx.getBean("beanSingleton");
		singleton2 = (BeanSingleton) appCtx.getBean("beanSingleton");
		prototype1 = (BeanPrototype) appCtx.getBean("beanPrototype");
		prototype2 = (BeanPrototype) appCtx.getBean("beanPrototype");
		beanCombinado = (BeanUsandoPrototypeAndSingleton) appCtx
				.getBean("beanUsandoPrototypeAndSingleton");
	}
	
	@After
	public void tearDown(){
		System.out.println("****************Separador de pruebas***********************");
	}

	@Test
	public void testAppCtx() {
		Assert.notNull(singleton1);
		Assert.notNull(singleton2);
		Assert.notNull(prototype1);
		Assert.notNull(prototype2);
		Assert.notNull(beanCombinado);
	}

	@Test
	public void testInstacia() {
		Assert.isTrue(singleton1 == singleton2,"Singleton 1 y 2 no son iguales");
		Assert.isTrue(prototype1 != prototype2,"prototype 1 y 2 son iguales");
		Assert.isTrue(beanCombinado.getBeanSingleton() == singleton1
				&& beanCombinado.getBeanSingleton() == singleton2,"el singleton del beanCombinado, singleton 1 y 2 son diferentes");
		Assert.isTrue(beanCombinado.getBeanPrototype() != prototype1
				&& beanCombinado.getBeanPrototype() != prototype2,"el prototype del beanCombinado, prototype 1, y son iguales");
		//¿Por qué...?
		System.out.println("Las direcciones de memoria son: ");
		System.out.println("singleton1: "+singleton1);
		System.out.println("singleton2: "+singleton2);
		System.out.println("prototype1: "+prototype1);
		System.out.println("prototype2: "+prototype2);
		System.out.println("beanSingleton dentro de beanCombinado: "+beanCombinado.getBeanSingleton());
		System.out.println("beanPrototype dentro de beanCombinado: "+beanCombinado.getBeanPrototype());
	}
	
	@Test
	public void testSingletonDentroDeSingleton(){
		beanCombinado.usandoSingleton();
	}
	
	@Test
	public void testPrototypeDentroDeSingleton(){
		beanCombinado.usandoPrototype();
	}
}
