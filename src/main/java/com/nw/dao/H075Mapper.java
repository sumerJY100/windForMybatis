package com.nw.dao;

import com.nw.model.H074;
import com.nw.model.H075;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface H075Mapper {
    String tableName = "h_075";
    @Delete({
        "delete from h_075",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into h_075 (id, v_time, ",
        "v)",
        "values (#{id,jdbcType=BIGINT}, #{vTime,jdbcType=TIMESTAMP}, ",
        "#{v,jdbcType=REAL})"
    })
    int insert(H075 record);

    @InsertProvider(type=H075SqlProvider.class, method="insertSelective")
    int insertSelective(H075 record);

    @Select({
        "select",
        "id, v_time, v",
        "from h_075",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="v_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="v", javaType=Float.class, jdbcType=JdbcType.REAL)
    })
    H075 selectByPrimaryKey(Long id);

    @UpdateProvider(type=H075SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(H075 record);

    @Update({
        "update h_075",
        "set v_time = #{vTime,jdbcType=TIMESTAMP},",
          "v = #{v,jdbcType=REAL}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(H075 record);


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
    int insertBatch(@Param("hList") List<H075> hList);
}