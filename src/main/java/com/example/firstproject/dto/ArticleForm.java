package com.example.firstproject.dto;

import org.springframework.web.bind.annotation.PostMapping;

import com.example.firstproject.entity.Article;

public class ArticleForm {
	
	private String title;
	private String content;
	
	
	public ArticleForm(String title, String content) {
		this.title = title;
		this.content = content;
	}



	 @Override
	    public String toString() {
	        return "ArticleForm{" +
	                "title='" + title + '\'' +
	                ", content='" + content + '\'' +
	                '}';
	    }



	public Article toEntity() {		// TODO Auto-generated method stub
		return new Article(null,title,content);
	}
}