package com.fengjx.ttwx.common.config;

 

import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private void readFromExternal() {
		String env = System.getProperty("ENV_MODE");
		 
		 
		Properties prop = System.getProperties();
		if (prop != null) {
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				appConfig.setProperty((String) entry.getKey(), (String) entry.getValue());
			}
		}
		
		for(Object key: appConfig.keySet()) {
			logger.info("config property [" + key + ": " + appConfig.getProperty(key.toString()) + "]");
		}
  }

	public Properties getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(Properties appConfig) {
		this.appConfig = appConfig;
	}

}
