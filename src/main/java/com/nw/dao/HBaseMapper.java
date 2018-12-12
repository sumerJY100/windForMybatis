package com.nw.dao;

import com.nw.model.H000;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HBaseMapper<T> {
    String tableName = "";
    @Delete({"delete from ",tableName})
    void deleteAll();
    @Insert({
            "<script>" +
                    "insert into ",tableName," ( v_time, v) values " ,
            "<foreach item='h' collection='hList' index='index' separator=','>" ,
            "(#{h.vTime,jdbcType=TIMESTAMP},#{h.v,jdbcType=REAL})" ,
            "</foreach>"
                    + "</script>"
    })
    int insertBatch(@Param("hList") List<T> hList);
}
