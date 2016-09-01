package com.jorge.service;

import java.util.List;

import com.jorge.model.User;

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

// CLIENT AND SERVER SIDE. We need the UserService interface, so that our application knows the methods available on the server service
public interface UserService {
	
	public abstract List<User> findAll();
	public abstract void addUser(User user);
	
}
