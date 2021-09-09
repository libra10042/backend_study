package com.cj.sbpy1.sevice;

import com.cj.sbpy1.dao.ArticleDao;
import com.cj.sbpy1.dto.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    public List<Article> getList() {
//        Article article = new Article(1, "20210909", "1", "2");
//        Article article2 = new Article(2, "20210909", "1", "2");
//        Article article3 = new Article(3, "20210909", "1", "2");
//        List<Article>  list = new ArrayList<>();
//
//
//        list.add(article);
//        list.add(article2);
//        list.add(article3);
        return articleDao.getList() ;

    }

    @Override
    public void add(Map<String, Object> param) {
        articleDao.add(param);
    }


}
