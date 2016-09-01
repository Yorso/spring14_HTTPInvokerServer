/**
 * This is a configuration class
 * 
 */

package com.jorge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jorge.service.UserService;
import com.jorge.service.UserServiceImpl;

/**
 * Using the Java RMI, HTTP Invoker, Hessian, and REST
 * 
 * 		HTTP Invoker to interact with another Spring application
 * 		Java RMI to interact with another Java application not using Spring
 * 		Hessian to interact with another Java application not using Spring when you need to go over	proxies and firewalls
 * 		SOAP if you have to
 * 		REST for all other cases. REST is currently the most popular option; it's simple, flexible, and cross-platform
 *
 */
@Configuration // This declares it as a Spring configuration class
@EnableWebMvc // This enables Spring's ability to receive and process web requests. Necessary for interceptors too.
@ComponentScan(basePackages = { "com.jorge.controller" }) // This scans the com.jorge.controller package for Spring components

// @Import({ DatabaseConfig.class, SecurityConfig.class }) => //If you are using a Spring application without a 'ServletInitializer' class,
														      // you can include other configuration classes from your primary configuration class

public class AppConfig{

	 @Bean
	 // Necessary for Java HTTP Invoker service. This returns an instance of UserServiceImpl
	 // SERVER SIDE
	 public UserService userService() {
		 return new UserServiceImpl();
	 }
	
	 
	 /***************************************************************************************************
	  * Creating a Java HTTP Invoker service
	  * 
	  * HTTP Invoker, like the Java RMI, is a Java remote method invocation technology; here, a client
	  * executes a method located on a server-the HTTP invoker service. HTTP is used instead of a custom
	  * port, so it can go over proxies and firewalls. However, it's a Spring technology, so both the client and
	  * the server must use Java and Spring.
	  * We will set up an HTTP Invoker service that will expose the methods of a normal Java
	  * class. The service will be part of an existing Spring web application
	  * 
	  * HttpInvokerServiceExporter is a Spring class generating an HTTP Invoker service from a Java
	  * interface ( UserService ). For each method defined in UserService , the corresponding method from
	  * userService() , in UserServiceImpl , will be executed
	  * 
	  * The HTTP Invoker service is now available at the /userService URL of the Spring web application
	  * 
	  */
	 @Bean(name = "/userService") // The HTTP Invoker service is now available at the /userService URL of the Spring web application
	 // HTTP Invoker SERVER SIDE
	 public HttpInvokerServiceExporter httpInvokerServiceExporter() {
		 HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		 
		 exporter.setService(userService()); // Refers to 'public UserService userService()' method above
		 exporter.setServiceInterface(UserService.class); // Refers to UserService interface
		 
		 return exporter;
	 }
	 
}