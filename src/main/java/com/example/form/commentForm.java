package com.example.form;

/**
 * 投稿コメントに関するフォーム.
 * 
 * @author suzukikunpei
 *
 */
public class commentForm {

	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** 投稿ID */
	private String article_id;

	public Integer getIntArticleId() {
		return Integer.parseInt("article_id");
	}

	@Override
	public String toString() {
		return "commentForm [name=" + name + ", content=" + content + ", article_id=" + article_id + "]";
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

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

}
