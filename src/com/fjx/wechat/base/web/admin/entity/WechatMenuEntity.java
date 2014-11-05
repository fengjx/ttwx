package com.fjx.wechat.base.web.admin.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjx.common.bean.ToStringBase;
import com.fjx.common.utils.CommonUtils;

/**
 * 菜单表实体
 * 
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
@Table(name = "wechat_menu")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","sysUser"}) 
public class WechatMenuEntity extends ToStringBase {
	
	private static final long serialVersionUID = -3089454315694161799L;

	private String id;
	private String name;				//名称
	private String type;				//类型
	private String menu_key;			//菜单标识
	private String url;					//链接
	private Date in_time;				//添加时间
	private Date update_time;			//修改时间
	private int menu_level;				//级别
	private WechatMenuEntity parent;	//父级ID
	private Set<WechatMenuEntity> children;	//子菜单
	
	private SysUserEntity sysUser;			//用户
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(unique=true)
	public String getMenu_key() {
		return menu_key;
	}

	public void setMenu_key(String menu_key) {
		this.menu_key = menu_key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
	@Column(length=1)
	public int getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}

	@ManyToOne(fetch=FetchType.EAGER,optional=true,cascade={CascadeType.DETACH,CascadeType.MERGE})
	public WechatMenuEntity getParent() {
		return parent;
	}

	public void setParent(WechatMenuEntity parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent",fetch=FetchType.LAZY)
	@OrderBy("in_time")
	public Set<WechatMenuEntity> getChildren() {
		return children;
	}

	public void setChildren(Set<WechatMenuEntity> children) {
		this.children = children;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public SysUserEntity getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserEntity sysUser) {
		this.sysUser = sysUser;
	}
	
	@Transient
	public String getStr_in_time() {
		return CommonUtils.date2String(in_time);
	}
	@Transient
	public String getStr_update_time() {
		return CommonUtils.date2String(update_time);
	}
}
