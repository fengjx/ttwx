package com.fjx.wechat.base.admin.service;

import java.util.List;
import java.util.Map;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.MaterialEntity;
import com.fjx.wechat.base.admin.entity.SysUserEntity;


/**
 * 素材管理
 * @author fengjx xd-fjx@qq.com
 * @version MaterialService.java 2014年9月13日
 */
public interface MaterialService extends IBaseAbstractService<MaterialEntity> {
	
	/**
	 * 通过素材类型查询素材列表
	 * @param Type
	 * @return
	 */
	public Pagination<MaterialEntity> getListPageByType(String Type, SysUserEntity sysUser);
	
	/**
	 * 保存或更新素材
	 * @param material
	 * @param contents
	 */
	public void saveOrUpdate(MaterialEntity material, List<Map<String,String>> contents)throws Exception;
	
	/**
	 * 读取html内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String loadMaterialContentByUrl(String url) throws Exception ;
}
