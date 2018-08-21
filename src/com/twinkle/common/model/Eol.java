package com.twinkle.common.model;

import java.util.List;

import com.twinkle.common.model.base.BaseEol;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Eol extends BaseEol<Eol> {
	public static final Eol dao = new Eol().dao();
	
	private List<List<Eoltip>> list;

	public List<List<Eoltip>> getList() {
		return list;
	}

	public void setList(List<List<Eoltip>> list) {
		this.list = list;
	}
	
	
}