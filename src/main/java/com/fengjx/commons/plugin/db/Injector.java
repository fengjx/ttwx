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

    public static <T> T injectBean(Class<T> beanClass, HttpServletRequest request,
            boolean skipConvertError) {
        String beanName = beanClass.getSimpleName();
        return injectBean(beanClass, StrUtil.firstCharToLowerCase(beanName), request,
                skipConvertError);
    }

    @SuppressWarnings("unchecked")
    public static <T> T injectBean(Class<T> beanClass, String beanName, HttpServletRequest request,
            boolean skipConvertError) {
        Object bean = createInstance(beanClass);
        String modelNameAndDot = StrUtil.isNotBlank(beanName) ? beanName + "." : null;

        Map<String, String[]> parasMap = request.getParameterMap();
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            // only setter method
            if (!methodName.startsWith("set") || methodName.length() <= 3) {
                continue;
            }
            Class<?>[] types = method.getParameterTypes();
            if (types.length != 1) { // only one parameter
                continue;
            }
            String attrName = StrUtil.firstCharToLowerCase(methodName.substring(3));
            String paraName = modelNameAndDot != null ? modelNameAndDot + attrName : attrName;
            if (parasMap.containsKey(paraName)) {
                try {
                    String paraValue = request.getParameter(paraName);
                    Object value = paraValue != null ? TypeConverter.convert(types[0], paraValue)
                            : null;
                    method.invoke(bean, value);
                } catch (Exception e) {
                    if (!skipConvertError) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return (T) bean;
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
        for (Entry<String, String[]> entry : parasMap.entrySet()) {
            String paraName = entry.getKey();
            String attrName;
            if (modelNameAndDot != null) {
                if (paraName.startsWith(modelNameAndDot)) {
                    attrName = paraName.substring(modelNameAndDot.length());
                } else {
                    continue;
                }
            } else {
                attrName = paraName;
            }

            Class<?> colType = table.getColumnType(attrName);
            if (colType == null) {
                if (skipConvertError) {
                    continue;
                } else {
                    throw new RuntimeException(
                            "The model attribute " + attrName + " is not exists.");
                }
            }
            try {
                String[] paraValueArray = entry.getValue();
                String paraValue = (paraValueArray != null && paraValueArray.length > 0)
                        ? paraValueArray[0] : null;

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

}
