package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;


/**
 * 掲示板に関する情報を処理するコントローラ.
 * 
 * @author suzukikunpei
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {

	/**
	 * フォームのインスタンス化
	 * 
	 * @return 記事のフォーム
	 */
	@ModelAttribute
	public ArticleForm form() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm form2() {
		return new CommentForm();
	}

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 投稿内容を表示させる初期画面.
	 * 
	 * @param articleForm 記事のフォーム
	 * @param model       リクエストスコープ
	 * @return 掲示板初期画面
	 */
	@RequestMapping("")
	public String index(Model model) {

		List<Article> articleList = articleRepository.findAll();
		System.out.println("start");
		for (Article article : articleList) {
			List<Comment> commentList = commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);
			
		}
		model.addAttribute("articleList", articleList);

		return "bbs";

	}

	/**
	 * 投稿を行う.
	 * 
	 * @param articleForm 記事のフォーム
	 * @return 投稿者と投稿内容
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm) {
		Article article = new Article();
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleRepository.insert(article);
		return "redirect:/bbs";
	}

	/**
	 * コメントを投稿する.
	 * 
	 * @param commentForm コメントのフォーム
	 * @return 投稿コメント
	 */
	@RequestMapping("/insertComment")
	public String commentInsert(CommentForm commentForm) {
		Comment comment = new Comment();
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		comment.setArticleId(commentForm.getIntArticleId());
		commentRepository.insert(comment);
		return "redirect:/bbs";
	}
	
	/**
	 * 投稿を削除する
	 * @param id　投稿ID
	 * @return　削除終了後の投稿
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
		return "redirect:/bbs";
		}
	}


