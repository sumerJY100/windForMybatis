package com.nw.dao;

import com.nw.model.H000;
import com.nw.model.H074;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface H074Mapper {
    public String tableName = "h_074";

    @Delete({
        "delete from h_074",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);


    @Insert({
        "insert into h_074 (id, v_time, ", "v)",
        "values (#{id,jdbcType=BIGINT}, #{vTime,jdbcType=TIMESTAMP}, ",
        "#{v,jdbcType=REAL})"
    })
    int insert(H074 record);
    @Insert({"<script>"+
            "insert into h_074 (id, v_time, v) values " +
            "<foreach item='h' collection='hList' index='index' separator=','>" +
            "(#{h.id,jdbcType=BIGINT},#{h.vTime,jdbcType=TIMESTAMP},#{h.v,jdbcType=REAL})"+
            "</foreach>"
            +"</script>"})
    void saveBatch(@Param("hList") List<H074> hList);
    @InsertProvider(type=H074SqlProvider.class, method="insertSelective")
    int insertSelective(H074 record);

    @Select({
        "select",
        "id, v_time, v",
        "from h_074",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="v_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="v", javaType=Float.class, jdbcType=JdbcType.REAL)
    })
    H074 selectByPrimaryKey(Long id);

    @UpdateProvider(type=H074SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(H074 record);

    @Update({
        "update h_074",
        "set v_time = #{vTime,jdbcType=TIMESTAMP},",
          "v = #{v,jdbcType=REAL}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(H074 record);



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
    int insertBatch(@Param("hList") List<H074> hList);
}