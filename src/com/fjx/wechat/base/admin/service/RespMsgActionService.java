package com.fjx.wechat.base.admin.service;

import java.util.Map;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.KeyWordActionView;
import com.fjx.wechat.base.admin.entity.MaterialEntity;
import com.fjx.wechat.base.admin.entity.RespMsgActionEntity;
import com.fjx.wechat.base.admin.entity.WechatMenuEntity;
import com.fjx.wechat.base.admin.entity.SysUserEntity;

/**
 * 微信消息响应规则接口
 * @author fengjx xd-fjx@qq.com
 * @version RespMsgActionService.java 2014年10月6日
 */
public interface RespMsgActionService extends IBaseAbstractService<RespMsgActionEntity> {
	
	
	/**
	 * 查询消息规则
	 * @param ext_type 扩展类型
	 * @param req_type 请求类型
	 * @param event_type 事件类型
	 * @param key_word 关键字/菜单key
	 * @param sysUser 归属用户
	 * @return
	 */
	public RespMsgActionEntity loadMsgAction(String ext_type, String req_type, String event_type, String key_word,SysUserEntity sysUser);
	
	/**
	 * 批量删除规则
	 * @param ids 规则ID，多个ID用","分隔
	 */
	public void deleteMsgActionById(String ids);
	
	/**
	 * 保存消息动作规则
	 * @param actionEntity
	 * @param menuEntity
	 * @param materialEntity
	 * @throws Exception
	 */
	public void saveAction(RespMsgActionEntity actionEntity,WechatMenuEntity menuEntity, MaterialEntity materialEntity);
	
	/**
	 * 更新消息动作规则
	 * @param actionEntity
	 * @param menuEntity
	 * @param materialEntity
	 */
	public void updateAction(RespMsgActionEntity actionEntity, WechatMenuEntity menuEntity, MaterialEntity materialEntity);
	
	/**
	 * 分页查询消息规则
	 * @param param keys[req_type, event_type, action_type, key_word, resp_type, start_time, end_time]
	 * @param sysUser 归属用户
	 * @return
	 */
	public Pagination<KeyWordActionView> pageMsgAction(Map<String, String> param, SysUserEntity sysUser);
	
	/**
	 * 根据关键字删除消息规则
	 * @param key_word
	 */
	public void deleteMsgActionByKey(String key_word);
	
}
