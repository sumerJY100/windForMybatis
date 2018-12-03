package com.nw.dao;

import com.nw.model.Blog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> findAll();

    @Select("select * from blog where id = #{id}")
    Blog findById(Integer id);
}