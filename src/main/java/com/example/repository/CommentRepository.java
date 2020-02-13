package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメントに関する情報を処理するリポジトリ.
 * 
 * @author suzukikunpei
 *
 */
@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setArticleId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};

	/**
	 * idを入力して対応したコメントを検索する.
	 * 
	 * @param id コメントID
	 * @return 入力したIDに対応したコメント
	 */
	public List<Comment> findById(int id) {
		String sql = "SELECT id,name,content,article_id FROM comments ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.query(sql, param, COMMENT_ROW_MAPPER);
	}

	/**
	 * コメントを投稿する.
	 * @param comment 投稿コメント
	 */
	public void insert(Comment comment) {
		String sql = "INSERT INTO comments (name,content,article_id) VALUES (:name,:content,:article_id)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
	}

}
