package com.xzymon.jee6app_client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.xzymon.jee6app.CounterBean;
import com.xzymon.jee6app.RemoteCounter;

public class CounterBeanClient {
	public static void main(String[] args) throws NamingException{
		invokeRemoteCounter();
	}
	
	public static void invokeRemoteCounter() throws NamingException{
		RemoteCounter counter = lookupRemoteCounter();
		String param = "MyName";
		System.out.format("EJB Response for %1$s is %2$d%n", param, counter.countCharacters(param));
	}
	
	public static RemoteCounter lookupRemoteCounter() throws NamingException{
		final Hashtable props = new Hashtable();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(props);
		
		String appName = "jee6app";
		String moduleName = "jee6app-ejb";
		String distinctName = "";
		String beanName = CounterBean.class.getSimpleName();
		String interfaceName = RemoteCounter.class.getName();
		String lookupName = String.format("ejb:%1$s/%2$s/%3$s/%4$s!%5$s", appName, moduleName, distinctName, beanName, interfaceName);
		return (RemoteCounter) context.lookup(lookupName);
	}
}
