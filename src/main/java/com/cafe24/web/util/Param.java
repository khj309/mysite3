package com.cafe24.web.util;

public class Param {

	private int pageNo;
	private int countPerPage;
	private String searchType;
	private String searchValue;
	private String queryString;
	private String shortQueryString;

	public Param(){
		this(1, 10, null, null);
	}

	public Param(int pageNo, int countPerPage){
		this(pageNo, countPerPage, null, null);
	}

	public Param(int pageNo, int countPerPage, String searchType, String searchValue) {
		this.pageNo = pageNo;
		this.countPerPage = countPerPage;
		this.searchType = searchType;
		this.searchValue = searchValue;

		//url에 쓸 쿼리스트링 만들기
		if((searchType != null && "".equals(searchType)==false) && (searchValue != null && "".equals(searchValue)==false)) {
			queryString = "&pageNo="+this.pageNo+"&searchType="+this.searchType+"&searchValue="+this.searchValue;
		}else {
			queryString = "&pageNo="+this.pageNo;
		}

		//url에 쓸 WKfqdms 쿼리스트링 만들기
		if((searchType != null && "".equals(searchType)==false) && (searchValue != null && "".equals(searchValue)==false)) {
			shortQueryString = "&searchType="+this.searchType+"&searchValue="+this.searchValue;
		}else {
			shortQueryString = "";
		}
	}

	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getCountPerPage() {
		return countPerPage;
	}


	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}


	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}



	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getShortQueryString() {
		return shortQueryString;
	}

	public void setShortQueryString(String shortQueryString) {
		this.shortQueryString = shortQueryString;
	}

	public void build() {

		//url에 쓸 쿼리스트링 만들기
		if((searchType != null && "".equals(searchType)==false) && (searchValue != null && "".equals(searchValue)==false)) {
			queryString = "&pageNo="+this.pageNo+"&searchType="+this.searchType+"&searchValue="+this.searchValue;
		}else {
			queryString = "&pageNo="+this.pageNo;
		}

		//url에 쓸 WKfqdms 쿼리스트링 만들기
		if((searchType != null && "".equals(searchType)==false) && (searchValue != null && "".equals(searchValue)==false)) {
			shortQueryString = "&searchType="+this.searchType+"&searchValue="+this.searchValue;

		}else {
			shortQueryString = "";
		}
	}

	@Override
	public String toString() {
		return "Param [pageNo=" + pageNo + ", countPerPage=" + countPerPage + ", searchType=" + searchType
				+ ", searchValue=" + searchValue + "]";
	}

	public Param clone(){ 
		Param newParam = new Param(); 
		
		newParam.setPageNo(pageNo);
		newParam.setCountPerPage(countPerPage);
		newParam.setQueryString(queryString);
		newParam.setSearchType(searchType);
		newParam.setSearchValue(searchValue);
		newParam.setShortQueryString(shortQueryString);

		return newParam;
	}
}
