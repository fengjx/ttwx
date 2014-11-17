package com.fjx.wechat.base.admin.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fjx.common.bean.ToStringBase;

import java.util.Date;

/**
 * 数据字典表
 * @author fengjx xd-fjx@qq.com
 * @version DataDictEntity.java 2014年9月13日
 */
@Table(name = "wechat_data_dict")
@Entity
public class DataDictEntity extends ToStringBase {

	private static final long serialVersionUID = -7305297007871570270L;

	private String id;
	private String group_code;			//分组代码
	private String group_name;			//分组名称
	private String dict_value;			//字典值
	private String dict_name;			//字典名称
	private String dict_desc;			//说明
	private String parent_id;			//父级ID
	private String order_num;			//排序
	private Date in_time;				//插入时间
	private String is_valid;			//是否启用
	
	
	
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

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getDict_value() {
		return dict_value;
	}

	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}

	public String getDict_name() {
		return dict_name;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	public String getDict_desc() {
		return dict_desc;
	}

	public void setDict_desc(String dict_desc) {
		this.dict_desc = dict_desc;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}

}
