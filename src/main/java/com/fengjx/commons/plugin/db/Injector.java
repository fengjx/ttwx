/**
 * Copyright (c) 2011-2016, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fengjx.commons.plugin.db;

import com.fengjx.commons.utils.StrUtil;
import com.fengjx.commons.utils.TypeConverter;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Injector.
 */
public class Injector {

    private static <T> T createInstance(Class<T> objClass) {
        try {
            return objClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends BaseBean> T injectModel(Class<T> modelClass,
                                                     HttpServletRequest request, boolean skipConvertError) {
        String modelName = modelClass.getSimpleName();
        return injectModel(modelClass, StrUtil.firstCharToLowerCase(modelName), request,
                skipConvertError);
    }

    public static <T extends BaseBean> T injectModel(Class<T> modelClass,
                                                     Map<String, String[]> parasMap, boolean skipConvertError) {
        String modelName = modelClass.getSimpleName();
        return injectModel(modelClass, StrUtil.firstCharToLowerCase(modelName), parasMap,
                skipConvertError);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseBean> T injectModel(Class<T> modelClass, String modelName,
                                                     HttpServletRequest request, boolean skipConvertError) {
        return injectModel(modelClass, modelName, request.getParameterMap(), skipConvertError);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseBean> T injectModel(Class<T> modelClass, String modelName,
                                                     Map<String, String[]> parasMap, boolean skipConvertError) {
        Object temp = createInstance(modelClass);
        BaseBean model = (BaseBean) temp;
        Table table = TableUtil.getTable(model.getClass());
        if (table == null) {
            throw new RuntimeException("The Table mapping of model: " + modelClass.getName()
                    + " not exists or the ActiveRecordPlugin not start.");
        }
        String modelNameAndDot = StrUtil.isNotBlank(modelName) ? modelName + "." : null;
        // 对 paraMap进行遍历而不是对table.getColumnTypeMapEntrySet()进行遍历，以便支持
        // CaseInsensitiveContainerFactory
        // 以及支持界面的 attrName有误时可以感知并抛出异常避免出错
        Map<String, Method> methodMap = null;
        for (Entry<String, String[]> entry : parasMap.entrySet()) {
            String[] paraValueArray = entry.getValue();
            String paraValue = (paraValueArray != null && paraValueArray.length > 0)
                    ? paraValueArray[0] : null;
            String paraName = entry.getKey();
            String attrName;
            if (modelNameAndDot != null && paraName.startsWith(modelNameAndDot)) {
                attrName = paraName.substring(modelNameAndDot.length());
            } else {
                attrName = paraName;
            }
            Class<?> colType = table.getColumnType(attrName);
            if (colType == null) {
                if (null == methodMap) {
                    methodMap = getMethodMap(modelClass);
                }
                String methodName = "set" + StrUtil.firstCharToUpperCase(attrName);
                if (MapUtils.isNotEmpty(methodMap)
                        && methodMap.containsKey(methodName)) {
                    Method method = methodMap.get(methodName);
                    Class<?>[] types = method.getParameterTypes();
                    if (types.length == 1) { // only one parameter
                        try {
                            Object value = paraValue != null
                                    ? TypeConverter.convert(types[0], paraValue) : null;
                            method.invoke(model, value);
                        } catch (Exception e) {
                            if (!skipConvertError) {
                                throw new RuntimeException("Can not set parameter: " + paraName, e);
                            }
                        }
                        continue;
                    }
                }
                model.set(attrName, paraValue);
                continue;
            }
            try {
                Object value = paraValue != null ? TypeConverter.convert(colType, paraValue) : null;
                model.set(attrName, value);
            } catch (Exception e) {
                if (!skipConvertError) {
                    throw new RuntimeException("Can not convert parameter: " + paraName, e);
                }
            }
        }
        return (T) model;
    }

    private static <T extends BaseBean> Map<String, Method> getMethodMap(Class<T> modelClass) {
        if (null == modelClass) {
            return null;
        }
        Method[] methods = modelClass.getMethods();
        Map<String, Method> methodMap = Maps.newHashMap();
        for (Method m : methods) {
            methodMap.put(m.getName(), m);
        }
        return methodMap;
    }

}
