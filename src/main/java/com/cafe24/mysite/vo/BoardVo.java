package com.cafe24.mysite.vo;

import java.util.List;

public class BoardVo {
	private long no;
	private String title;
	private String content;
	private String regDate;
	private int groupNo;
	private int orderNo;
	private int depth;
	private long userNo;
	private long viewCnt;
	private String writer;
	private List<CommentVo> comment;
	private String status;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public long getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(long viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public List<CommentVo> getComment() {
		return comment;
	}
	public void setComment(List<CommentVo> comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", groupNo="
				+ groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userNo=" + userNo + ", viewCnt=" + viewCnt
				+ ", writer=" + writer + ", status=" + status + "]";
	}
}
