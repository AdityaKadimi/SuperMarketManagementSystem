package com.uncc.ssdi.supermarket_management_system.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeVo {

	ProductVo data;
	
	List<TreeVo> children=new ArrayList<>();
	
	public TreeVo() {
		super();
	}

	public ProductVo getData() {
		return data;
	}

	public void setData(ProductVo value) {
		this.data = value;
	}

	public List<TreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}

	
	
	
}
