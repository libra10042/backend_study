package com.cj.sbpy1.controller.article;

import com.cj.sbpy1.dto.Article;
import com.cj.sbpy1.sevice.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j // log.info 가능.
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    // 게시글 리스트
    @RequestMapping("/list")
    public String showList(Model model){
        List<Article> list = articleService.getList();
        log.info("list : " + list);
        model.addAttribute("list", list);
        return "article/list";
    }

    // 게시글 추가화면
    @RequestMapping("/add")
    public String showAdd(){
//@RequestParam(required = false) String title , @RequestParam(required = false) String body
        log.info("Add test");
        return "article/add";
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public String doAdd(@RequestParam Map<String, Object> param){
        articleService.add(param);

        return "게시물이 추가되었습니다.";
    }
}
