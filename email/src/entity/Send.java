package entity;

import java.util.Date;

public class Send {
	private int id;
	private String fromname;
	private String title;
	private String content;
	private int state;
	private String toname;
	private String createdate;
	public Send() {
		super();
	}
	public Send(int id, String fromname, String title, String content,
			int state, String toname, String createdate) {
		super();
		this.id = id;
		this.fromname = fromname;
		this.title = title;
		this.content = content;
		this.state = state;
		this.toname = toname;
		this.createdate = createdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getToname() {
		return toname;
	}
	public void setToname(String toname) {
		this.toname = toname;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
}
