package com.example.form;

/**
 * 記事に関するフォーム.
 * 
 * @author suzukikunpei
 *
 */
public class ArticleForm {

	/**投稿者名*/
	private String name;
	/**投稿内容*/
	private String content;
	/**投稿ID*/
	private Integer id;
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + ", id=" + id + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
}
