package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.form.ArticleForm;

/**
 * 記事に関する情報を処理するリポジトリ.
 * 
 * @author suzukikunpei
 *
 */
@Repository
public class ArticleRepository {
	
	@Autowired
	public ArticleForm form() {
		return new ArticleForm();
	}
	

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};

	/**
	 * 記事に関する情報の全件検索を行う.
	 * 
	 * @return 全件検索結果
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}

	/**
	 * 記事の内容を入力する.
	 * 
	 * @param article 記事
	 * @param name    投稿者名
	 * @param content 記事の内容
	 * @return  追加する投稿内容
	 */
	public void insert(Article article) {
		
		String sql = "INSERT INTO articles (name, content) VALUES (:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name",article.getName()).addValue("content",article.getContent());
		 template.update(sql, param);
	}

	/**
	 * 入力したIDの記事を削除.
	 * 
	 * @param id 記事ID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
