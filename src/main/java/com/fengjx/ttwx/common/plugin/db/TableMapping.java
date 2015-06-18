
package com.fengjx.ttwx.common.plugin.db;

import java.util.HashMap;
import java.util.Map;

/**
 * TableMapping save the mapping between model class and table.
 */
class TableMapping {

    private final Map<Class<? extends Model>, Table> modelToTableMap = new HashMap();

    private static TableMapping me = new TableMapping();

    private TableMapping() {
    }

    protected static TableMapping me() {
        return me;
    }

    public void putTable(Table table) {
        modelToTableMap.put(table.getModelClass(), table);
    }

    public Table getTable(Class<? extends Model> modelClass) {
        Table table = modelToTableMap.get(modelClass);
        if (table == null)
            throw new RuntimeException(
                    "The Table mapping of model: "
                            + modelClass.getName()
                            + " not exists. Please add mapping package to TableMappingPlugin for spring config ");

        return table;
    }
}
