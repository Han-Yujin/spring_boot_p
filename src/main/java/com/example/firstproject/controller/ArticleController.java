package com.example.firstproject.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;
@Slf4j // 로깅을 위한 어노테이션 추가 

@Controller
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
    	log.info(form.toString());
        //System.out.println(form.toString());
        
        //1. DTO를 엔터리로 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());
        //2. 리파지터리로 엔터티를  DB에 저장 
        
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/"+ saved.getId();
    }
    
    @GetMapping("/articles/{id}")//데이터 조회 요청 접수 
    public String show (@PathVariable Long id ,Model model) {
    	log.info("id =" +id);// id를 잘 받았는지 확인하는 로그 찍기 
    	//1. id 조회에 데이터 가져오기
    	Article articleEntity = articleRepository.findById(id).orElse(null);
    	//2. 모델에 데이터 등록하기 
    	model.addAttribute("article",articleEntity);
    	//3. 뷰 페이지 변환하기 
    	
    	
    	return "articles/show";
    }
    
    
    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        Iterable<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
    	//수정할 데이터 가져오기 
    	Article articleEntity = articleRepository.findById(id).orElse(null);
    	//모델에 데이터 등록하기 
    	model.addAttribute("article",articleEntity);
    	//뷰 페이지 설정하기 
    	return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
    	log.info(form.toString()); // 데이터 잘 받았는지 확인 
    	//1. DTO를 엔티티로 변경
    	Article articleEntity = form.toEntity();
    	log.info(articleEntity.toString());
    	//2. 엔티티를 DB에 저장
    	//2-1 DB에서 기존데이터 가져오기 
    	Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
    	//2-2 기존 데이터 값 갱신하기 
    	if (target != null) {
    		articleRepository.save(articleEntity);// 엔티티를 DB에 저장(갱신)
    	}
    	//3. 수정 결과 페이지로 리다이렉트하기 
    	return "redirect:/articles/"+articleEntity.getId();
    }
}