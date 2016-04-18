
package com.fengjx.modules.sys.service;

import com.fengjx.commons.plugin.cache.IDataLoader;
import com.fengjx.commons.plugin.cache.ehcache.EhCacheUtil;
import com.fengjx.commons.plugin.db.Model;
import com.fengjx.commons.plugin.db.Page;
import com.fengjx.commons.plugin.db.Record;
import com.fengjx.commons.plugin.freemarker.FreemarkerUtil;
import com.fengjx.commons.utils.JsonUtil;
import com.fengjx.commons.utils.StrUtil;
import com.fengjx.modules.common.constants.AppConfig;
import com.fengjx.modules.common.constants.FtlFilenameConstants;
import com.fengjx.modules.sys.bean.SysDict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Autu Generated .
 */
@Component
public class SysDictService extends Model<SysDict> {

    public static final String DICT_MAP = "dict_map";

    public static final String DICT_JSON_MAP = "dictJsonMap";

    private static final String ORDER_BY = " order by in_time desc, order_no desc";

    /**
     * 根据字典值获得字典名称
     *
     * @param value
     * @param group
     * @return
     */
    public String getDictLabel(String value, String group) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        Map<String, Object> attrs = Maps.newHashMap();
        attrs.put("dict_value", value);
        attrs.put("group_code", group);
        Record record = findOne(attrs);
        if (record.isEmpty()) {
            return "";
        }
        return StringUtils.defaultIfEmpty(record.getStr("dict_name"), "");
    }

    /**
     * 根据指点标签和分组获得字典值
     *
     * @param label
     * @param group
     * @return
     */
    public String getDictValue(String label, String group) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("dict_name", label);
        attrs.put("group_code", group);
        return findOne(attrs).getStr("dict_value");
    }

    /**
     * 根据字典分组获得字典列表
     *
     * @param group
     * @return
     */
    public List<Map<String, Object>> findDictList(String group) {
        Map<String, Object> attrs = new HashMap<>();
        attrs.put("group_code", group);
        return findList(attrs, ORDER_BY);
    }

    public Map<String, List<SysDict>> dictMapList(){
        return EhCacheUtil.get(AppConfig.EhcacheName.DICT_CACHE, DICT_MAP, new IDataLoader<Map<String, List<SysDict>>>() {
            @Override
            public Map<String, List<SysDict>> load() {
                Map<String, List<SysDict>> resMap = Maps.newHashMap();
                List<SysDict> dictList = find(null);
                if(CollectionUtils.isNotEmpty(dictList)){
                    for(SysDict dict : dictList){
                        List<SysDict> list = resMap.get(dict.getGroupCode());
                        if(null == list){
                            list = Lists.newArrayList();
                        }
                    }

                }


                return resMap;
            }
        });
    }

    /**
     * 分页查询
     *
     * @param dict 查询参数
     * @return
     */
    public Page<Map<String, Object>> findPage(SysDict dict) {
        StringBuilder sql = new StringBuilder(getSelectSql("a"));
        sql.append(" where 1 = 1");
        List<Object> qryParams = new ArrayList<>();
        if (StringUtils.isNoneBlank(dict.getGroupCode())) {
            sql.append(" and group_code like CONCAT('%',?,'%')");
            qryParams.add(dict.getGroupCode());
        }
        if (StringUtils.isNoneBlank(dict.getDictDesc())) {
            sql.append(" and dict_desc like CONCAT('%',?,'%')");
            qryParams.add(dict.getDictDesc());
        }
        if (null != dict.getIsValid()) {
            sql.append(" and is_valid = ?");
            qryParams.add(dict.getIsValid());
        }
        sql.append(ORDER_BY);
        return paginate(sql.toString(), qryParams.toArray());
    }




    public String jsTemplate() {
        return EhCacheUtil.get(AppConfig.EhcacheName.DICT_CACHE, DICT_JSON_MAP, () -> {
            StringBuilder groupSql = new StringBuilder(
                    "select distinct(group_code) group_code from ");
            groupSql.append(getTableName());
            List<Map<String, Object>> groupList = findList(groupSql.toString());
            if (CollectionUtils.isEmpty(groupList)) {
                return "{}";
            }
            Map<String, Object> res = Maps.newHashMap();
            for (Map<String, Object> group : groupList) {
                String groupCode = (String) group.get("group_code");
                List<Map<String, Object>> dataList = findList(createSql(), groupCode);
                res.put(groupCode, dataList);
            }
            Map<String, String> attr = Maps.newHashMap();
            attr.put("data", StrUtil.replaceBlank(JsonUtil.toJson(res)));
            return FreemarkerUtil.process(attr, FtlFilenameConstants.DICT_JS);
        });
    }

    private String createSql() {
        StringBuilder sql = new StringBuilder(getSelectSql());
        sql.append(" where group_code = ? ").append(" order by order_no , in_time desc");
        return sql.toString();
    }

}
