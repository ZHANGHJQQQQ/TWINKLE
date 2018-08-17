package com.jfinal.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseGlobalcount<M extends BaseGlobalcount<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setIp(java.lang.String ip) {
		set("ip", ip);
		return (M)this;
	}
	
	public java.lang.String getIp() {
		return getStr("ip");
	}

	public M setNum(java.lang.Integer num) {
		set("num", num);
		return (M)this;
	}
	
	public java.lang.Integer getNum() {
		return getInt("num");
	}

	public M setDates(java.util.Date dates) {
		set("dates", dates);
		return (M)this;
	}
	
	public java.util.Date getDates() {
		return get("dates");
	}

}
