package template.Application.controller;

public class Notice_data {

	private int noticeid;
	private String title;
	private String content;
	private int viewcount;

	public Notice_data() {
	}

	public Notice_data(int noticeid, String title) {
		super();
		this.noticeid = noticeid;
		this.title = title;
	}

	public Notice_data(int noticeid, String title, String content, int viewcount) {
		super();
		this.noticeid = noticeid;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
	}


	@Override
	public String toString() {
		return "Notice_data [noticeid=" + noticeid + ", title=" + title + ", content=" + content + ", viewcount="
				+ viewcount + "]";
	}

	public int getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(int noticeid) {
		this.noticeid = noticeid;
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

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}


}