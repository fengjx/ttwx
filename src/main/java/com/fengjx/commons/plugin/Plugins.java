
package com.fengjx.commons.plugin;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Plugins.
 */
public class Plugins {

    private static final Logger LOG = LoggerFactory.getLogger(Plugins.class);

    private List<IPlugin> pluginList = new ArrayList<>();

    public void init() {
        if (CollectionUtils.isEmpty(pluginList)) {
            LOG.info("pluginList is null，no plugin will be start");
        } else {
        	for (IPlugin p : pluginList) {
        		  p.start();
                LOG.info(p.getClass().getSimpleName() + " started...");
			}
        	//java 8 style
//            pluginList.forEach(p -> {
//                p.start();
//                LOG.info(LOG, p.getClass().getSimpleName() + " started...");
//            });
        }
    }

    public List<IPlugin> getPluginList() {
        return pluginList;
    }

    public void setPluginList(List<IPlugin> pluginList) {
        this.pluginList = pluginList;
    }
}
