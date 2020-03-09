package com.twinkle.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseJwglttb<M extends BaseJwglttb<M>> extends Model<M> implements IBean {

	public M setId(Integer id) {
		set("id", id);
		return (M)this;
	}

	public Integer getId() {
		return getInt("id");
	}

	public M setUserid(Integer userid) {
		set("userid", userid);
		return (M)this;
	}

	public Integer getUserid() {
		return getInt("userid");
	}

	public M setWeek1(String week1) {
		set("week1", week1);
		return (M)this;
	}

	public String getWeek1() {
		return getStr("week1");
	}

	public M setWeek2(String week2) {
		set("week2", week2);
		return (M)this;
	}

	public String getWeek2() {
		return getStr("week2");
	}

	public M setWeek3(String week3) {
		set("week3", week3);
		return (M)this;
	}

	public String getWeek3() {
		return getStr("week3");
	}

	public M setWeek4(String week4) {
		set("week4", week4);
		return (M)this;
	}

	public String getWeek4() {
		return getStr("week4");
	}

	public M setWeek5(String week5) {
		set("week5", week5);
		return (M)this;
	}

	public String getWeek5() {
		return getStr("week5");
	}

	public M setDates(java.util.Date dates) {
		set("dates", dates);
		return (M)this;
	}
	
	public java.util.Date getDates() {
		return get("dates");
	}

}
