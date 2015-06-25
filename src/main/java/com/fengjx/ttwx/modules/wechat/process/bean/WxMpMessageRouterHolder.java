
package com.fengjx.ttwx.modules.wechat.process.bean;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;

/**
 * @author fengjx.
 * @date：2015/6/22 0022
 */
public class WxMpMessageRouterHolder {

    private static volatile WxMpMessageRouter router;

    public static WxMpMessageRouter getRouter() {
        return router;
    }

    public static void setRouter(WxMpMessageRouter router) {
        WxMpMessageRouterHolder.router = router;
    }
}
