package com.fengjx.commons.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 
 * @author dennykong
 *
 */
public class PropertiesHolder {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesHolder.class);

	public final static PropertiesHolder INSTANCE = new PropertiesHolder();

	private Properties appConfig = new Properties();

	private PropertiesHolder() {
	}
 

	public Properties getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(Properties appConfig) {
		this.appConfig = appConfig;
	}

	public String getValue(String key){
		return getAppConfig().getProperty(key);
	}
}
