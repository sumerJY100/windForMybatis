package com.nw.dao;

import com.nw.model.H000;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface H000Mapper {
    public String tableName = "h_000";


    @Delete({
            "delete from ", tableName ," ",
            " where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into ",tableName," (id, v_time, ",
            "v)",
            "values (#{id,jdbcType=BIGINT}, #{vTime,jdbcType=TIMESTAMP}, ",
            "#{v,jdbcType=REAL})"
    })
    @Options(useGeneratedKeys = true,keyColumn = "id")
    int insert(H000 record);
//    @Insert({
//            "<script>" +
//                "insert into h_000 (id, v_time, v) values " +
//                "<foreach item='h' collection='hList' index='index' separator=','>" +
//                "(#{h.id,jdbcType=BIGINT},#{h.vTime,jdbcType=TIMESTAMP},#{h.v,jdbcType=REAL})" +
//                "</foreach>"
//            + "</script>"
//    })



    @InsertProvider(type = H000SqlProvider.class, method = "insertSelective")
    int insertSelective(H000 record);

    @Select({
            "select",
            "id, v_time, v",
            "from h_000",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
            @Arg(column = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT, id = true),
            @Arg(column = "v_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Arg(column = "v", javaType = Float.class, jdbcType = JdbcType.REAL)
    })
    H000 selectByPrimaryKey(Long id);

    @UpdateProvider(type = H000SqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(H000 record);

    @Update({
            "update h_000",
            "set v_time = #{vTime,jdbcType=TIMESTAMP},",
            "v = #{v,jdbcType=REAL}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(H000 record);

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
    int insertBatch(@Param("hList") List<H000> hList);
}