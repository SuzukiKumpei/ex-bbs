package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

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

	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 投稿内容を表示させる初期画面.
	 * 
	 * @param articleForm 記事のフォーム
	 * @param model       リクエストスコープ
	 * @return 投稿内容
	 */
	@RequestMapping("")
	public String index(Model model) {
//		Article article = new Article();
//		article.setName(articleForm.getName());
//		article.setContent(articleForm.getContent());
		model.addAttribute("articleList", articleRepository.findAll());
		return "bbs";
	}

	/**
	 * 投稿を行う.
	 * 
	 * @param articleForm
	 * @return 投稿者と投稿内容
	 */
	@RequestMapping("/insert")
	public String insertArticle(ArticleForm articleForm) {
		Article article = new Article();
		article.setName(articleForm.getName());
		article.setContent(articleForm.getContent());
		articleRepository.insert(article);
		return "redirect:/bbs";
	}
	
	@RequestMapping("/insert")
	public String commentInsert() {
		Comment comment = new Comment();
		
	}

}
