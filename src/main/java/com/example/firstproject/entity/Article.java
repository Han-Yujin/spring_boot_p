package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Article {
	@Id       // 엔티티의 대표값 지정
	@GeneratedValue	// 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
	private Long id;  
	@Column  // title 필드 선연, DB테이블의 title열과 연결
	private String title; 
	@Column	 // content필드 선언 
	
	private String content;
	public Article(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;

	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	


}
