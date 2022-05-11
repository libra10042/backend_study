package com.cj.sbpy1.sevice;

import com.cj.sbpy1.dto.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    public List<Article> getList();

    public void add(Map<String, Object> param);
}
