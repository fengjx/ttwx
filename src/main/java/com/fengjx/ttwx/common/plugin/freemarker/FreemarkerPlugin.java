
package com.fengjx.ttwx.common.plugin.freemarker;

import com.fengjx.ttwx.common.plugin.IPlugin;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * freemark²å¼þ
 *
 * @Created by FengJianxin on 2015/8/22.
 * @Email xd-fjx@qq.com
 */
public class FreemarkerPlugin implements IPlugin {

    private FreeMarkerConfigurer configurer;

    @Override
    public void start() {
        FreemarkerUtil.init(configurer.getConfiguration());
    }

    public FreeMarkerConfigurer getConfigurer() {
        return configurer;
    }

    public void setConfigurer(FreeMarkerConfigurer configurer) {
        this.configurer = configurer;
    }

}
