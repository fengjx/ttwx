package com.fjx.wechat.base.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fjx.wechat.base.admin.entity.KeyWordActionView;
import com.fjx.wechat.base.admin.entity.MaterialEntity;
import com.fjx.wechat.base.admin.entity.RespMsgActionEntity;
import com.fjx.wechat.base.admin.entity.WechatMenuEntity;
import com.fjx.wechat.mysdk.constants.WechatMenuConstants;
import com.fjx.wechat.mysdk.constants.WechatRespMsgtypeConstants;
import com.fjx.wechat.mysdk.tools.MaterialUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.CommonUtils;
import com.fjx.wechat.base.admin.entity.SysUserEntity;


/**
 * 消息动作规则service
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月9日
 */
@Service
public class RespMsgActionServiceImpl extends BaseAbstractService<RespMsgActionEntity> implements RespMsgActionService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	/*
	 * 保存消息动作规则
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.admin.service.RespMsgActionService#saveAction(com.fjx.wechat.base.admin.entity.RespMsgActionEntity, com.fjx.wechat.base.admin.entity.WechatMenuEntity, com.fjx.wechat.base.admin.entity.MaterialEntity)
	 */
	@Override
	public void saveAction(RespMsgActionEntity actionEntity, WechatMenuEntity menuEntity, MaterialEntity materialEntity){
		if(null != menuEntity){	//菜单参数为空，表示不是菜单动作
			saveMenuAction(actionEntity, menuEntity, materialEntity);
		}else{
			saveMsgAction(actionEntity, materialEntity);
		}
	}
		
	/*
	 * 更新消息动作规则
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.admin.service.RespMsgActionService#updateAction(com.fjx.wechat.base.admin.entity.RespMsgActionEntity, com.fjx.wechat.base.admin.entity.WechatMenuEntity, com.fjx.wechat.base.admin.entity.MaterialEntity)
	 */
	@Override
	public void updateAction(RespMsgActionEntity actionEntity, WechatMenuEntity menuEntity, MaterialEntity materialEntity){
		String action_id = actionEntity.getId();
		if(StringUtils.isNotBlank(action_id)){		//如果是点击类型，先之前的删除消息动作规则
			deleteMsgActionById(action_id);
		}
		saveAction(actionEntity, menuEntity, materialEntity);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.admin.service.RespMsgActionService#loadMsgAction(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RespMsgActionEntity loadMsgAction(String ext_type, String req_type, String event_type, String key_word, SysUserEntity sysUser){
		RespMsgActionEntity actionEntity = null;
		List<String> parameters = new ArrayList<String>();
		StringBuffer hql = new StringBuffer("from RespMsgActionEntity a "); 
		hql.append("where a.sysUser.id = ? ");
		parameters.add(sysUser.getId());
		if(StringUtils.isNotBlank(ext_type)){
			hql.append(" and a.ext_type = ?");
			parameters.add(ext_type);
		}
		if(StringUtils.isNotBlank(req_type)){
			hql.append(" and a.req_type = ?");
			parameters.add(req_type);
		}
		if(StringUtils.isNotBlank(event_type)){
			hql.append(" and a.event_type = ?");
			parameters.add(event_type);
		}
		if(StringUtils.isNotBlank(key_word)){
			hql.append(" and a.key_word = ?");
			parameters.add(key_word);
		}
		actionEntity = findOneByHql(hql.toString(), parameters);
		return actionEntity;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.admin.service.RespMsgActionService#deleteMsgActionById(java.lang.String)
	 */
	@Override
	public void deleteMsgActionById(String ids) {
		if(null == ids || "".equals(ids)){
			throw new MyRuntimeException("ID为空，删除消息动作失败");
		}
		String _ids[] = ids.split(",");
		if(null != _ids && _ids.length>0){
			for(String id : _ids){
				delete(id);
			}
		}else{
			delete(ids);
		}
	}
	
	/*
	 * 根据关键字删除消息规则
	 * (non-Javadoc)
	 * @see com.fjx.wechat.base.admin.service.RespMsgActionService#deleteMsgActionByKey(java.lang.String)
	 */
	@Override
	public void deleteMsgActionByKey(String key_word){
		String hql = "delete RespMsgActionEntity a where a.key_word = ?";
		bulkUpdate(hql, true, key_word);
	}
	
	
	@Override
	public Pagination<KeyWordActionView> pageMsgAction(Map<String, String> param, SysUserEntity sysUser) {
		List<Object> parameters = new ArrayList<Object>();
//		StringBuffer sql = new StringBuffer("select a.id as id, a.req_type as req_type, a.action_type as action_type, a.key_word as key_word, a.material_id as material_id, a.app_id as app_id, a.in_time as in_time,");
//				sql.append(" b.bean_name as bean_name, b.method_name as method_name, b.name as app_name,");
//				sql.append(" c.xml_data as xml_data, c.msg_type as msg_type,");
//				sql.append(" d.dict_name as dict_name");
//				sql.append(" from wechat_resp_msg_action a");
//				sql.append(" left join wechat_ext_app b ");
//				sql.append(" on a.app_id = b.id");
//				sql.append(" left join wechat_material c");
//				sql.append(" on a.material_id = c.id");
//				sql.append(" left join wechat_data_dict d");
//				sql.append(" on c.msg_type = d.dict_value");
//				sql.append(" where d.group_code = 'resp_type' ");
//				sql.append(" and a.user_id = ? ");
		StringBuffer hql = new StringBuffer("select new com.fjx.wechat.base.admin.entity.KeyWordActionView( ");
				hql.append(" a.id as id, a.req_type as req_type, a.action_type as action_type, a.key_word as key_word, a.in_time as in_time,");
				hql.append(" b.id as app_id, b.bean_name as method_name, b.method_name as method_name, b.name as app_name,");
				hql.append(" c.id as material_id, c.xml_data as xml_data, c.msg_type as msg_type,");
				hql.append(" d.dict_name as dict_name )");
				hql.append(" from RespMsgActionEntity as a, DataDictEntity d");
				hql.append(" left join a.extApp as b ");
				hql.append(" left join a.material as c");
				hql.append(" where a.action_type=d.dict_value");
				hql.append(" and d.group_code = 'action_type' ");
				hql.append(" and a.sysUser.id = ? ");
//		StringBuffer sql = new StringBuffer("select a.id id, a.req_type req_type, a.action_type action_type, a.key_word key_word, a.material_id material_id, a.app_id app_id, a.in_time in_time, a.user_id user_id, a.beanName beanName, a.methodName methodName, a.app_name app_name, a.xml_data xml_data, a.msg_type msg_type, a.dict_name dict_name ");
//		StringBuffer hql = new StringBuffer("select a.id as id, a.req_type as req_type, a.action_type as action_type, a.key_word as key_word, a.material_id as material_id, a.app_id as app_id, a.in_time as in_time, a.user_id as user_id, a.beanName as beanName, a.methodName as methodName, a.app_name as app_name, a.xml_data as xml_data, a.msg_type as msg_type, a.dict_name as dict_name ");
//		StringBuffer hql = new StringBuffer();
//			hql.append("from KeyWordActionView a");
//			hql.append(" where a.user_id = ? ");	
		parameters.add(sysUser.getId());
		if(StringUtils.isNotBlank(param.get("req_type"))){
			hql.append(" and a.req_type = ?");
			parameters.add(param.get("req_type"));
		}
		if(StringUtils.isNotBlank(param.get("event_type"))){
			hql.append(" and a.event_type = ?");
			parameters.add(param.get("event_type"));
		}
		if(StringUtils.isNotBlank(param.get("action_type"))){
			hql.append(" and a.action_type = ?");
			parameters.add(param.get("action_type"));
		}
		if(StringUtils.isNotBlank(param.get("key_word"))){
			hql.append(" and a.key_word like ?");
			parameters.add("%"+param.get("key_word")+"%");
		}
		if(StringUtils.isNotBlank(param.get("start_time"))){
			hql.append(" and a.in_time >= ?");
			parameters.add(CommonUtils.string2Date(param.get("start_time").trim()+" 00:00:00"));
		}
		if(StringUtils.isNotBlank(param.get("end_time"))){
			hql.append(" and a.in_time < ?");
			parameters.add(CommonUtils.string2Date(param.get("end_time").trim()+" 23:59:59"));
		}
		hql.append(" order by a.in_time desc");
		return pageByHql(hql.toString(), parameters);
	}

	/**
	 * 保存菜单动作
	 * @param actionEntity
	 * @param menuEntity
	 * @param materialEntity
	 */
	private void saveMenuAction(RespMsgActionEntity actionEntity, WechatMenuEntity menuEntity, MaterialEntity materialEntity){
		Date now = new Date();
		
		String menu_id = menuEntity.getId();
		menuEntity.setUpdate_time(now);
		actionEntity.setIn_time(now);
		String menuType = menuEntity.getType();
		
		//菜单类型为click
		if(menuType.equals(WechatMenuConstants.TYPE_CLICK)){
			menuEntity.setMenu_key("key_"+menu_id);
			menuEntity.setUrl(null);
			actionEntity.setKey_word("key_"+menu_id);			//请求关键字 or 菜单点击key
			
			//消息响应类型
			String action_type = actionEntity.getAction_type();
			if(RespMsgActionEntity.ACTION_TYPE_MATERIAL.equals(action_type)){		//从素材读取数据
				//消息响应类型
				String resp_type = actionEntity.getMaterial().getMsg_type();
				//消息回复类型是文字，则先将文字信息保存到素材表
				if(resp_type.equals(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT)){
					materialEntity.setIn_time(now);
					materialEntity.setMsg_type(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
					
					List<Map<String, String>> materialList = new ArrayList<Map<String,String>>();
					Map<String, String> materiaParam = new HashMap<String, String>();
					materiaParam.put("msg_type", materialEntity.getMsg_type());
					materiaParam.put("txt_content", materialEntity.getContent());
					materialList.add(materiaParam);
					String materialXml = MaterialUtil.data2Xml(materialList);
					materialEntity.setXml_data(materialXml);
					logger.debug("materiaXmlParam json data: {} "+ materialXml);
					
					actionEntity.setMaterial(materialEntity);
					save(materialEntity);
				}
			}
			save(actionEntity);
		}else if(menuType.equals(WechatMenuConstants.TYPE_VIEW)){
			menuEntity.setMenu_key(null);
			
		}
		update(menuEntity);
	}


	/**
	 * 保存其他（除菜单外）消息动作
	 * @param actionEntity
	 * @param materialEntity
	 */
	private void saveMsgAction(RespMsgActionEntity actionEntity, MaterialEntity materialEntity){

		Date now = new Date();
		actionEntity.setIn_time(now);
		
		//消息响应类型
		String action_type = actionEntity.getAction_type();
		if(RespMsgActionEntity.ACTION_TYPE_MATERIAL.equals(action_type)){		//从素材读取数据
			//消息响应类型
			String resp_type = actionEntity.getMaterial().getMsg_type();
			//消息回复类型是文字，则先将文字信息保存到素材表
			if(resp_type.equals(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT)){
				materialEntity.setIn_time(now);
				materialEntity.setMsg_type(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
				
				List<Map<String, String>> materialList = new ArrayList<Map<String,String>>();
				Map<String, String> materiaParam = new HashMap<String, String>();
				materiaParam.put("msg_type", materialEntity.getMsg_type());
				materiaParam.put("txt_content", materialEntity.getContent());
				materialList.add(materiaParam);
				String materialXml = MaterialUtil.data2Xml(materialList);
				materialEntity.setXml_data(materialXml);
				logger.debug("materiaXmlParam json data: {} "+ materialXml);
				
				actionEntity.setMaterial(materialEntity);
				save(materialEntity);
			}
		}
		//保存前先判断改消息规则是否存在，某种规则必须确保唯一
		RespMsgActionEntity actionEntity2 = loadMsgAction(actionEntity.getExt_type(), actionEntity.getReq_type(), actionEntity.getEvent_type(), actionEntity.getKey_word(),actionEntity.getSysUser());
		if(null != actionEntity2){
			throw new MyRuntimeException("相同的消息动作已经存在");
		}
		save(actionEntity);
	}

}

