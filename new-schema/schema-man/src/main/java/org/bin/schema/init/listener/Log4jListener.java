//package org.bin.schema.init.listener;
//
//import java.io.File;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.apache.log4j.PropertyConfigurator;
//import org.bin.schema.util.StringUtil;
//
//@WebListener
//public class Log4jListener implements ServletContextListener{
//
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		String parentPath = System.getenv("ZHICALL_CONFIG");
//		String contextPath = arg0.getServletContext().getContextPath();
//		String log4jpath = StringUtil.strAdd(parentPath,File.separator,contextPath.substring(1),File.separator,"log4j.xml");
//		System.out.println(log4jpath);
//		PropertyConfigurator.configure(log4jpath);
//	}
//
//}
