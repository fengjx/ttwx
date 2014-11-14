package com.fjx.wechat.base.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.base.constants.WechatMenuConstants;
import com.fjx.wechat.base.tools.WeChatUtil;
import com.fjx.wechat.base.vo.menu.ClickButton;
import com.fjx.wechat.base.vo.menu.ComplexButton;
import com.fjx.wechat.base.vo.menu.Menu;
import com.fjx.wechat.base.vo.menu.ViewButton;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.base.admin.entity.WechatMenuEntity;


/**
 * 
 * @author fengjx xd-fjx@qq.com
 * @version WechatMenuServiceImpl.java 2014年9月26日
 */
@Service
public class WechatMenuServiceImpl extends BaseAbstractService<WechatMenuEntity> implements WechatMenuService {
	
	@Autowired
	private RespMsgActionService msgActionService;
	
	/*
	 * 保存菜单
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.web.admin.service.WechatMenuService#saveMenu(com.fjx.wechat.base.web.admin.entity.WechatMenuEntity, java.lang.String)
	 */
	@Override
	public void saveMenu(WechatMenuEntity menuEntity, String parent_id,SysUserEntity sysUser) {
		WechatMenuEntity parent = load(parent_id);
		menuEntity.setSysUser(sysUser);
		Date now = new Date();
		menuEntity.setUpdate_time(now);
		menuEntity.setIn_time(now);
		menuEntity.setParent(parent);
		save(menuEntity);
	}
	
	/*
	 * 更新菜单
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.web.admin.service.WechatMenuService#updateMenu(com.fjx.wechat.base.web.admin.entity.WechatMenuEntity, java.lang.String)
	 */
	@Override
	public void updateMenu(WechatMenuEntity menuEntity, String parent_id,SysUserEntity sysUser) {
		WechatMenuEntity parent = load(parent_id);
		Date now = new Date();
		menuEntity.setSysUser(sysUser);
		menuEntity.setUpdate_time(now);
		menuEntity.setParent(parent);
		update(menuEntity);
	}
	
	@Override
	public List<Map<String, Object>> treeMenu(SysUserEntity sysUser) {
		List<Map<String,Object>> res = null;
		res = this.loadMenuDetailById(null,sysUser.getId());
		return res;
	}
	
	private List<Map<String,Object>> loadMenuDetailById(String id, String userId){
		List<Map<String,Object>> res = null;
		StringBuffer sql = new StringBuffer("select m.id as \"id\", m.in_time as \"in_time\", m.menu_key as \"menu_key\", m.menu_level as \"menu_level\", m.name as \"name\", m.parent_id as \"parent_id\", m.type as \"type\", m.update_time as \"update_time\", m.url as \"url\", m.user_id as \"user_id\",");
					 sql.append(" a.id as \"action_id\", a.action_type as \"action_type\", a.in_time as \"action_time\", ");
					 sql.append(" b.id as \"app_id\",b.name as \"app_name\", b.description as \"app_description\",");
					 sql.append(" c.id as \"material_id\",c.xml_data as \"xml_data\" ");
					 sql.append(" from wechat_menu m ");
					 sql.append(" left join wechat_resp_msg_action a on m.menu_key = a.key_word ");
					 sql.append(" left join wechat_ext_app b on a.app_id = b.id ");
					 sql.append(" left join wechat_material c on a.material_id = c.id ");
					 sql.append(" where m.user_id = ?");
		
//		StringBuffer sql = new StringBuffer("select new map( ");
//			sql.append(" m.id as id, m.in_time as in_time, m.menu_key as menu_key, m.menu_level as menu_level, m.name as name, m.parent.id as parent_id, m.type as type, m.update_time as update_time, m.url as url, m.sysUser.id as user_id,");
//			sql.append(" a.id as action_id, a.action_type as action_type, a.in_time as action_time, ");
//			sql.append(" e.id as app_id, e.name as app_name, e.description as app_description,");
//			sql.append(" ma.id as material_id, ma.xml_data as xml_data) ");
//			sql.append(" from WechatMenuEntity m, RespMsgActionEntity a, ExtAppEntity e, MaterialEntity ma");
//			sql.append(" where m.sysUser.id = ? and m.menu_key = a.key_word and a.extApp.id = e.id and a.material.id = ma.id");
		
//		StringBuffer sql = new StringBuffer("select new map( ");
//			sql.append(" m.id as id, m.in_time as in_time, m.menu_key as menu_key, m.menu_level as menu_level, m.name as name, m.parent.id as parent_id, m.type as type, m.update_time as update_time, m.url as url, m.sysUser.id as user_id,");
//			sql.append(" a.id as action_id, a.action_type as action_type, a.in_time as action_time, ");
//			sql.append(" e.id as app_id, e.name as app_name, e.description as app_description,");
//			sql.append(" ma.id as material_id, ma.xml_data as xml_data) ");
//			sql.append(" from WechatMenuEntity m, RespMsgActionEntity a, ExtAppEntity e, MaterialEntity ma");
//			sql.append(" where m.sysUser.id = ? and m.menu_key = a.key_word and a.extApp.id = e.id and a.material.id = ma.id");
			
			
		if(StringUtils.isBlank(id)){
			sql.append(" and (m.parent_id is null or m.parent_id = '') order by m.in_time asc");
			res = findListMapBySql(sql.toString(),userId);
		}else{
			sql.append(" and m.parent_id = ? order by m.in_time asc");
			res = findListMapBySql(sql.toString(),userId, id);
		}
		if( null != res && res.size()>0 ){
			for (int i = 0,l = res.size(); i < l; i++) {
				String _id = res.get(i).get("id")+"";
				if(!isLeef(_id)){	//如果存在子节点（不是叶子节点），则继续递归查询
					List<Map<String,Object>> tmpList =  loadMenuDetailById(_id,userId);
					res.get(i).put("children", tmpList);
				}
			}
		}
		return res;
	}
	
	
	@Override
	public void deleteMenu(String id){
		if(StringUtils.isNotBlank(id)){
			delete(id);
			msgActionService.deleteMsgActionByKey("key_"+id);
		}
	}
	
	/*
	 * 菜单发布
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.web.admin.service.WechatMenuService#release(com.fjx.wechat.base.web.admin.entity.SysUser)
	 */
	@Override
	public void release(SysUserEntity sysUser) throws Exception{
		String appid = sysUser.getWechatPublicAccount().getApp_id();
		String appsecret = sysUser.getWechatPublicAccount().getApp_secret();
		Menu menu = loadMenu(sysUser);
		JSONObject res = WeChatUtil.createMenu(menu, appid, appsecret);
		String errcode = res.getString("errcode");
		if(!"0".equals(errcode)){
			throw new MyRuntimeException("菜单发布失败：errcode="+errcode+" and errmsg="+res.getString("errmsg"));
		}
	}
	
	
	/**
	 * 查询树形菜单列表(只包含菜单数据)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Menu loadMenu(SysUserEntity sysUser) {
		Menu menu = null;
		List<WechatMenuEntity> res = null;
		String hql = "from WechatMenuEntity m where (m.parent.id = null or m.parent.id = '') and m.sysUser = ? order by m.in_time asc";
		res = findListByHql(hql,sysUser);
		if( null != res && res.size()>0 ){
			menu = new Menu();
			for (WechatMenuEntity m : res) {
				String _name = m.getName();
				Set<WechatMenuEntity> children = m.getChildren();
				if(null != children && children.size() > 0){	//如果存在子节点（不是叶子节点），则继续查询
					ComplexButton button = new ComplexButton();
					button.setName(_name);
					List<WechatMenuEntity> tmpList = new ArrayList<WechatMenuEntity>(children);
					for(WechatMenuEntity s_m : tmpList){
						String s_type = s_m.getType();
						String s_name = s_m.getName();
						if(s_type.equals(WechatMenuConstants.TYPE_CLICK)){
							ClickButton s_button = new ClickButton();
							s_button.setName(s_name);
							s_button.setKey(s_m.getMenu_key());
							button.addButton(s_button);
						}else if(s_type.equals(WechatMenuConstants.TYPE_VIEW)){
							ViewButton s_button = new ViewButton();
							s_button.setName(s_name);
							s_button.setUrl(s_m.getUrl());
							button.addButton(s_button);
						}else{
							throw new MyRuntimeException("菜单【"+s_name+"】未设置动作");
						}
					}
					menu.addButton(button);
				}else{
					String type = m.getType();
					if(WechatMenuConstants.TYPE_CLICK.equals(type)){
						ClickButton button = new ClickButton();
						button.setName(_name);
						button.setKey(m.getMenu_key());
						menu.addButton(button);
					}else if(WechatMenuConstants.TYPE_VIEW.equals(type)){
						ViewButton button = new ViewButton();
						button.setName(_name);
						button.setUrl(m.getUrl());
						menu.addButton(button);
					}else{
						throw new MyRuntimeException("菜单【"+_name+"】未设置动作");
					}
				}
			}
		}
		return menu;
	} 
	
	/**
	 * 判断菜单是否是叶子节点
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private boolean isLeef(String id){
		String hql = "from WechatMenuEntity m where m.parent.id = ?";
		int count = getCount(hql, true, id);
		if(count > 0){
			return false;
		}
		return true;
	}

	
}
