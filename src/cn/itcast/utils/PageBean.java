package cn.itcast.utils;

import java.util.List;

public class PageBean {
	//列表数据 lmit ?,?
	private List list;//dao直接查询
	//当前页数
	private Integer currentPage;//页面传参
	//总记录数
	private Integer totalCount;//dao直接查询
	//每页显示条数
	private Integer pageSize;////页面传参
	//总页数
	private Integer totalPage;//(根据总记录数与每页显示条数)计算得出
	
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		
		//校验当前页数
		//不为空
		//大于等于1
		if(currentPage==null||currentPage < 1){
			//为空=>设为默认值1
			this.currentPage = 1;
		}
		//校验每页显示条数
		if(pageSize==null){
			//为空=>设为默认每页显示3条
			this.pageSize = 3;
		}
		//不为空
		
		//封装计算总页数功能
		//方案1:取模判断是否有余数.
		//方案2:Math中的ceil方法
		//方案3:数学计算
		this.totalPage = (this.totalCount+this.pageSize-1)/this.pageSize;
		
		//小于等于总页数
		if(this.currentPage > this.totalPage){
			//大于总页数,显示最后一页
			this.currentPage = this.totalPage;
		}
	}
	//获得查询起始索引
	public Integer getStart() {
		//(当前页数-1)*每页显示条数
		return (this.currentPage-1)*this.pageSize;
	}
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}	
	
	
	
}
