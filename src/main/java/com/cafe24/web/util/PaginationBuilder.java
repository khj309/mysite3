package com.cafe24.web.util;

public class PaginationBuilder {
	private Param param;
	private long totalBoardCount;
	private int pagiCountPerPage;
	private int countTotalPage;
	private int countTotalPagination;
	private int paginationNo;
	private int startPageNo;
	private int endPageNo;
	private boolean hasNextPagination;
	private boolean hasPreviousPagination;

	public PaginationBuilder(long totalBoardCount) {
		this(new Param(), totalBoardCount ,5);
	}

	public PaginationBuilder(Param param, long totalBoardCount, int pagiCountPerPage) {
		this.param = param;
		this.totalBoardCount = totalBoardCount;
		this.pagiCountPerPage = pagiCountPerPage;
		hasNextPagination = false;
		hasPreviousPagination = false;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public long getTotalBoardCount() {
		return totalBoardCount;
	}

	public void setTotalBoardCount(long totalBoardCount) {
		this.totalBoardCount = totalBoardCount;
	}

	public int getPagiCountPerPage() {
		return pagiCountPerPage;
	}

	public void setPagiCountPerPage(int pagiCountPerPage) {
		this.pagiCountPerPage = pagiCountPerPage;
	}

	public int getCountTotalPage() {
		return countTotalPage;
	}

	public void setCountTotalPage(int countTotalPage) {
		this.countTotalPage = countTotalPage;
	}

	public int getCountTotalPagination() {
		return countTotalPagination;
	}

	public void setCountTotalPagination(int countTotalPagination) {
		this.countTotalPagination = countTotalPagination;
	}

	public int getPaginationNo() {
		return paginationNo;
	}

	public void setPaginationNo(int paginationNo) {
		this.paginationNo = paginationNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public boolean isHasNextPagination() {
		return hasNextPagination;
	}

	public void setHasNextPagination(boolean hasNextPagination) {
		this.hasNextPagination = hasNextPagination;
	}

	public boolean isHasPreviousPagination() {
		return hasPreviousPagination;
	}

	public void setHasPreviousPagination(boolean hasPreviousPagination) {
		this.hasPreviousPagination = hasPreviousPagination;
	}

	public void build() {
		int pageNo=param.getPageNo();
		int countPerPage = param.getCountPerPage();

		//총 페이지 번호 갯수
		countTotalPage = (int) Math.ceil((double)totalBoardCount/countPerPage);

		//총 페이지 번호 그룹 갯수
		countTotalPagination = (int) Math.ceil((double)countTotalPage/pagiCountPerPage);

		//현재 페이지의 페이지 그룹 번호
		paginationNo = (int) Math.ceil(((double)pageNo/pagiCountPerPage));

		//시작 페이지번호
		startPageNo = ((paginationNo-1)*pagiCountPerPage)+1; 
		//끝 페이지번호
		endPageNo = startPageNo+pagiCountPerPage-1; 

		//다음 페이지 그룹이 있는가?
		if(paginationNo != countTotalPagination) {
			hasNextPagination = true;
		}

		//이전 페이지 그룹이 있는가?
		if(paginationNo != 1) {
			hasPreviousPagination = true;
		}
	}

	@Override
	public String toString() {
		return "PaginationBuilder [param=" + param + ", totalBoardCount=" + totalBoardCount + ", pagiCountPerPage="
				+ pagiCountPerPage + ", countTotalPage=" + countTotalPage + ", countTotalPagination="
				+ countTotalPagination + ", paginationNo=" + paginationNo + ", startPageNo=" + startPageNo
				+ ", endPageNo=" + endPageNo + ", hasNextPagination=" + hasNextPagination + ", hasPreviousPagination="
				+ hasPreviousPagination + "]";
	}
}
