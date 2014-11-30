package com.fjx.wechat.base.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.CommonUtils;
import com.fjx.wechat.base.admin.entity.ExtAppSupportTypeEntity;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.wechat.base.admin.entity.ExtAppEntity;



/**
 * 应用扩展管理
 * @author fengjx xd-fjx@qq.com
 * @version ExtAppServiceImpl.java 2014年9月13日
 */
@Service
public class ExtAppServiceImpl extends BaseAbstractService<ExtAppEntity> implements ExtAppService {

	/**
	 *
	 * @param app_type 应用类型 web、api、restful
	 * @param msg_type
	 * @param event_type
	 * @return
	 */
	@Override
	public List<ExtAppEntity> listByType(String app_type, String msg_type, String event_type){
		if (StringUtils.isBlank(app_type)) {
			throw new IllegalArgumentException("app_type不能为空！");
		}
		List<String> params = new ArrayList<String>();
		params.add(app_type);
		String hql = " from ExtAppEntity e where e.app_type = ?";
		if (StringUtils.isNotBlank(msg_type)) {
			hql = "select s.extApp from ExtAppSupportTypeEntity s where s.extApp.app_type = ?";
			hql += " and s.msg_type = ?";
			params.add(msg_type);
			if (StringUtils.isNotBlank(event_type)) {
				hql += " and s.event_type = ?";
				params.add(event_type);
			}else{
				hql += " and (s.event_type = '' or s.event_type is null)";
			}
		}
		return findListByHql(hql, params);
	}


	@Override
	public Pagination<ExtAppEntity> pageList(ExtAppEntity extApp) {
		StringBuilder hql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		hql.append("select e from ExtAppEntity e where 1 = 1");
		if (StringUtils.isNotBlank(extApp.getApp_type())) {
			hql.append(" and e.app_type = ?");
			params.add(extApp.getApp_type());
		}
		if (StringUtils.isNotBlank(extApp.getName())) {
			hql.append(" and e.name like ? ");
			params.add("%"+extApp.getName()+"%");
		}
		if(StringUtils.isNotBlank(extApp.getStart_time())){
			hql.append(" and e.in_time > ?");
			params.add(CommonUtils.String2Date(extApp.getStart_time() + " 00:00:00"));
		}
		if(StringUtils.isNotBlank(extApp.getEnd_time())){
			hql.append(" and e.in_time < ?");
			params.add(CommonUtils.String2Date(extApp.getEnd_time()+" 23:59:59"));
		}
		return pageByHql(hql.toString(),params);
	}

	/**
	 * 添扩展应用
	 *
	 * @param extApp
	 * @param reqTypes   关联的消息类型（多个参数用,分隔）
	 * @param eventTypes 关联的事件类型（多个参数用,分隔，当消息类型是event时此参数才生效）
	 */
	@Override
	public void saveOrUpdateApp(ExtAppEntity extApp, String reqTypes, String eventTypes) {
		extApp.setIn_time(new Date());
		//id不为空，表示更新
		if(StringUtils.isNotBlank(extApp.getId())){
			String hql = "delete from ExtAppSupportTypeEntity t where t.extApp = ? ";
			bulkUpdate(hql,true,extApp);
			update(extApp);
		}else{
			save(extApp);
		}
		//非页面应用（接口类）
		if(!ExtAppEntity.TYPE_WEB.equals(extApp.getApp_type())){
			List<ExtAppSupportTypeEntity> extApps = new ArrayList<ExtAppSupportTypeEntity>();
			ExtAppSupportTypeEntity supportType = null;
			String[] reqTypeArr = reqTypes.split(",");
			for(String reqType : reqTypeArr){
				if (WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT.equals(reqType)) {
					String[] eventTypeArr = eventTypes.split(",");
					for(String eventType : eventTypeArr){
						supportType = new ExtAppSupportTypeEntity();
						supportType.setExtApp(extApp);
						supportType.setMsg_type(reqType);
						supportType.setEvent_type(eventType);
						extApps.add(supportType);
					}
				}else{
					supportType = new ExtAppSupportTypeEntity();
					supportType.setExtApp(extApp);
					supportType.setMsg_type(reqType);
					extApps.add(supportType);
				}
			}
			saveList(extApps);
		}
	}

	/**
	 * 删除扩展应用
	 * @param id
	 */
	@Override
	public void deleteApp(String id) {
		String hql = "delete from ExtAppSupportTypeEntity t where t.extApp.id = ? ";
		bulkUpdate(hql,true,id);
		delete(id);
	}


}
