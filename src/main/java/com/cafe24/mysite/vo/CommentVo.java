package com.cafe24.mysite.vo;

public class CommentVo {
	private long no;
	private long userNo;
	private String userName;
	private String content;
	private String regDate;
	private long boardNo;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", userNo=" + userNo + ", userName=" + userName + ", content=" + content
				+ ", regDate=" + regDate + ", boardNo=" + boardNo + "]";
	}
}
