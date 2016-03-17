
package com.fengjx.modules.api.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by fengjx on 2015/9/8.
 */
public class Consts {

    public static class Apistore {

        public static final String API_KEY = "99ace48c9d18240efdcae951209a1cd1";

        public static final Map<String, String> API_KEY_MAP = new HashMap<>();

        static {
            API_KEY_MAP.put("apikey", API_KEY);
        }

    }

}
