package com.cj.sbpy1.dao;

import com.cj.sbpy1.dto.Article;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ArticleDaoImpl implements ArticleDao{
    @Override
    public List<Article> getList() {
        List<Article> list = new ArrayList<>();


        return list;
    }

    @Override
    public void add(Map<String, Object> param) {

    }
}
