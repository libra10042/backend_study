package com.cj.sbpy1.dao;


import com.cj.sbpy1.dto.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper // 이렇게 해주면 ArticleDao 구현체를 마이바티스가 대신 구현해준다.
public interface ArticleDao {
    public List<Article> getList();

    public void add(Map<String, Object> param);

}
