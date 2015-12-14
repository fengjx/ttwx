
package com.fengjx.commons.plugin.cache.ehcache;

import com.fengjx.commons.plugin.IPlugin;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * EhCachePlugin.
 */
public class EhCachePlugin implements IPlugin {

    private static final Logger LOG = LoggerFactory.getLogger(EhCachePlugin.class);

    private CacheManager cacheManager;
    private String configurationFileName;
    private URL configurationFileURL;

    public EhCachePlugin() {

    }

    public EhCachePlugin(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public EhCachePlugin(String configurationFileName) {
        this.configurationFileName = configurationFileName;
    }

    public EhCachePlugin(URL configurationFileURL) {
        this.configurationFileURL = configurationFileURL;
    }

    @Override
    public void start() {
        createCacheManager();
        EhCacheUtil.init(cacheManager);
    }

    private void createCacheManager() {
        if (cacheManager != null) {
            return;
        }
        if (configurationFileName != null) {
            cacheManager = CacheManager.create(configurationFileName);
            return;
        }
        if (configurationFileURL != null) {
            cacheManager = CacheManager.create(configurationFileURL);
            return;
        }
        cacheManager = CacheManager.create();
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public String getConfigurationFileName() {
        return configurationFileName;
    }

    public void setConfigurationFileName(String configurationFileName) {
        this.configurationFileName = configurationFileName;
    }

    public URL getConfigurationFileURL() {
        return configurationFileURL;
    }

    public void setConfigurationFileURL(URL configurationFileURL) {
        this.configurationFileURL = configurationFileURL;
    }
}
