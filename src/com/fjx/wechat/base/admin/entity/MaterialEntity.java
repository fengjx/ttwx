package com.fjx.wechat.base.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 素材表实体
 * @JsonIgnoreProperties 
 * 是因为jsonplugin用的是java的内审机制.hibernate会给被管理的pojo加入一个hibernateLazyInitializer属性,
 * jsonplugin会把hibernateLazyInitializer也拿出来操作,并读取里面一个不能被反射操作的属性就产生了这个异常.
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
@Table(name = "wechat_material")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","sysUser"}) 
public class MaterialEntity extends ToStringBase {
	
	private static final long serialVersionUID = 3880248855755446046L;
	
	private String id;
	private String msg_type;		//素材类型(text,news。。。)
	private String xml_data;		//素材xml数据
	private Date in_time;			//保存时间
	private SysUserEntity sysUser;		//添加（归属）用户
	
	private String content;			//素材内容，不需要映射
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	
	@Column(length=2048,nullable=false)
	public String getXml_data() {
		return xml_data;
	}
	
	public void setXml_data(String xml_data) {
		this.xml_data = xml_data;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	public SysUserEntity getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserEntity sysUser) {
		this.sysUser = sysUser;
	}
	
	@Transient
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}
}
