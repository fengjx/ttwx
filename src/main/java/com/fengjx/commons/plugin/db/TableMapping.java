
package com.fengjx.commons.plugin.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TableMapping save the mapping between model class and table.
 */
class TableMapping {

    private final Map<Class<? extends BaseBean>, Table> modelToTableMap = new ConcurrentHashMap<>();

    private static TableMapping me = new TableMapping();

    private TableMapping() {
    }

    protected static TableMapping me() {
        return me;
    }

    public void putTable(Table table) {
        modelToTableMap.put(table.getModelClass(), table);
    }

    public Table getTable(Class<? extends BaseBean> beanClass) {
        Table table = modelToTableMap.get(beanClass);
        if (table == null) {
            throw new RuntimeException("The Table mapping of bean: " + beanClass.getName()
                    + " not exists. Please add mapping package to TableMappingPlugin for spring config ");
        }
        return table;
    }
}
