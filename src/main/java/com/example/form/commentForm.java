package com.example.form;

/**
 * 投稿コメントに関するフォーム.
 * 
 * @author suzukikunpei
 *
 */
public class CommentForm {
	/** コメントID */
    private String id;
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** 投稿ID */
	private String articleId;
	
	

	public Integer getIntArticleId() {
		return Integer.parseInt(articleId);
	}
	
	public Integer getIntId() {
		return Integer.parseInt(id);
	}

	@Override
	public String toString() {
		return "CommentForm [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	

	
	
}
