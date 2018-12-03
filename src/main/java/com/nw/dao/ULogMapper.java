package com.nw.dao;

import com.nw.model.ULog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ULogMapper {
    @Delete({
        "delete from u_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into u_log (id, l_user_id, ",
        "l_log_time, l_loginOrlogOut, ",
        "l_note)",
        "values (#{id,jdbcType=BIGINT}, #{lUserId,jdbcType=BIGINT}, ",
        "#{lLogTime,jdbcType=TIMESTAMP}, #{lLoginorlogout,jdbcType=INTEGER}, ",
        "#{lNote,jdbcType=VARCHAR})"
    })
    int insert(ULog record);

    int insertSelective(ULog record);

    @Select({
        "select",
        "id, l_user_id, l_log_time, l_loginOrlogOut, l_note",
        "from u_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    ULog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ULog record);

    @Update({
        "update u_log",
        "set l_user_id = #{lUserId,jdbcType=BIGINT},",
          "l_log_time = #{lLogTime,jdbcType=TIMESTAMP},",
          "l_loginOrlogOut = #{lLoginorlogout,jdbcType=INTEGER},",
          "l_note = #{lNote,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(ULog record);
}