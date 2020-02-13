package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事に関する情報を処理するリポジトリ.
 * @author suzukikunpei
 *
 */
@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs,i) ->{
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	
	/**
	 * 記事に関する情報の全件検索を行う.
	 * @return 全件検索結果
	 */
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM articles ORDER BY id";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
	/**
	 * 記事の内容を入力する.
	 * @param article 記事
	 * @param name　投稿者名
	 * @param content　記事の内容
	 */
	public void insert(Article article,String name,String content) {
		String sql = "INSERT INTO articles(name,content) VALUES(:name,:content)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", name).addValue("content",content);
		template.queryForObject(sql, param, ARTICLE_ROW_MAPPER);
	}
	
	/**
	 * 入力したIDの記事を削除.
	 * @param id 記事ID
	 */
	public void deleteById(int id) {
		String sql = "DELETE id,name,content FROM articles WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
		template.queryForObject(sql, param, ARTICLE_ROW_MAPPER);
	}
	

}