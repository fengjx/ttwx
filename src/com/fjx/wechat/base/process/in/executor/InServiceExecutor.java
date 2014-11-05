package com.fjx.wechat.base.process.in.executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.common.framework.system.init.FreeMarkerUtil;
import com.fjx.common.framework.system.init.SpringBeanFactoryUtil;
import com.fjx.wechat.base.constants.FtlFilenameConstants;
import com.fjx.wechat.base.context.WechatContext;
import com.fjx.wechat.base.process.ServiceExecutor;
import com.fjx.wechat.base.process.ServiceExecutorNameWire;
import com.fjx.wechat.base.web.admin.entity.RespMsgActionEntity;
import com.fjx.wechat.base.web.admin.service.RespMsgActionService;
import com.fjx.wechat.base.web.admin.service.WechatPublicAccountService;

/**
 * 业务执行接口
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public abstract class InServiceExecutor implements ServiceExecutor,	ServiceExecutorNameWire {
	
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected RespMsgActionService msgActionService;
	
	@Autowired
	protected WechatPublicAccountService wechatPublicAccountService;
	
	/**
	 * 执行消息动作
	 * @param ext_type	自定义类型
	 * @param req_type	请求类型
	 * @param event_type	事件类型
	 * @param key_word	关键字/key
	 * @throws Exception 
	 */
	protected String doAction(String ext_type, String req_type,String event_type,String key_word) throws Exception {
		RespMsgActionEntity actionEntity = msgActionService.loadMsgAction(ext_type, req_type, event_type, key_word, WechatContext.getPublicAccount().getSysUser());
		if(null == actionEntity){
			//返回默认消息
			return FreeMarkerUtil.process(null, FtlFilenameConstants.WECHAT_DEFAULT_MSG);
		}
		String res = null;
		String actionType = actionEntity.getAction_type();
		if(RespMsgActionEntity.ACTION_TYPE_MATERIAL.equals(actionType)){	//从素材取数据
			res = actionEntity.getMaterial().getXml_data();
		}else if(RespMsgActionEntity.ACTION_TYPE_API.equals(actionType)){	//从接口返回数据
			res = busiappHandle(actionEntity.getExtApp().getBeanName(), actionEntity.getExtApp().getMethodName());
		}
		return res;
	}

	/**
	 * 扩展业务处理
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	protected String busiappHandle(String beanName, String methodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		// 从spring中拿到业务bean
		Object o = SpringBeanFactoryUtil.getBean(beanName);
		// 通过反射调用业务bean的方法
		Method method = o.getClass().getMethod(methodName);
		Object res = method.invoke(o, null);
		logger.debug("beanName：" + beanName + " methodName：" + methodName
				+ "接口返回数据：" + res.toString());
		return res.toString();
	}
	
}
