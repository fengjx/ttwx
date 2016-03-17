
package com.fengjx.commons.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * 自定义property配置加载
 * 
 * @author dennykong
 */
public class ConfigPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private boolean printConfigOut = true;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        try {
            super.loadProperties(props);
        } catch (IOException ex) {
            logger.warn(ex.getMessage() + ", will use default configuration.");
        }
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        String env = System.getProperty("ENV_MODE");
        logger.info("ENV_MODE is " + env);
        Properties appConfig = PropertiesHolder.INSTANCE.getAppConfig();
        mergeProperties(props, appConfig);
        super.processProperties(beanFactoryToProcess, appConfig);
    }

    protected void mergeProperties(Properties propsSrc, Properties propsDest) {
        for (Object obj : propsSrc.keySet()) {
            String key = obj.toString();
            if (!propsDest.containsKey(obj)) {
                String value = propsSrc.getProperty(key);
                propsDest.setProperty(key, value);
                if (printConfigOut) {
                    logger.info("config property [" + key + ": " + value + "]");
                }
            }
        }
    }

    public boolean isPrintConfigOut() {
        return printConfigOut;
    }

    public void setPrintConfigOut(boolean printConfigOut) {
        this.printConfigOut = printConfigOut;
    }
}
