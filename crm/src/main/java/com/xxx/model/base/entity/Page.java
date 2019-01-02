package com.xxx.model.base.entity;

//使用传递前三个参数的构造方法
public class Page {
	//当前页（前台传）
	private int currentPage;
	//每页条数（自定义）
	private int pageCount;
	//总条数（查询获取）
	private int totalCount;
	//总页数（自动计算）
	private int totalPage;
	//索引（自动计算）
	private int index;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Page() {
		super();
	}
	/**
	 * 当前页，每页条数，总条数
	 */
	public Page(int currentPage, int pageCount, int totalCount) {
		super();
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		//计算总页数、索引
		this.totalPage = (totalCount+pageCount-1)/pageCount;
		this.index = (currentPage-1)*pageCount;
	}
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageCount=" + pageCount + ", totalCount=" + totalCount
				+ ", totalPage=" + totalPage + ", index=" + index + "]";
	}
	
}
