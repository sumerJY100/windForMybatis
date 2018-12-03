package com.nw.dao;

import com.nw.model.CoalPipingHistoryMillA;
import com.nw.model.CoalPipingHistoryMillC;
import com.nw.model.CoalPipingHistoryMillD;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CoalPipingHistoryMillDMapper {
    @Delete({
        "delete from dcoal_piping_history",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into dcoal_piping_history (id, h_coal_mill_id, ",
        "h_time, h_pipeA_Velocity, ",
        "h_pipeB_Velocity, h_pipeC_velocity, ",
        "h_pipeD_velocity, h_pipeA_dencity, ",
        "h_pipeB_dencity, h_pipeC_dencity, ",
        "h_pipeD_dencity, h_pipeA_x, ",
        "h_pipeA_y, h_pipeA_v, h_pipeB_x, ",
        "h_pipeB_y, h_pipeB_v, h_pipeC_x, ",
        "h_pipeC_y, h_pipeC_v, h_pipeD_x, ",
        "h_pipeD_y, h_pipeD_v)",
        "values (#{id,jdbcType=BIGINT}, #{hCoalMillId,jdbcType=BIGINT}, ",
        "#{hTime,jdbcType=TIMESTAMP}, #{hPipeaVelocity,jdbcType=REAL}, ",
        "#{hPipebVelocity,jdbcType=REAL}, #{hPipecVelocity,jdbcType=REAL}, ",
        "#{hPipedVelocity,jdbcType=REAL}, #{hPipeaDencity,jdbcType=REAL}, ",
        "#{hPipebDencity,jdbcType=REAL}, #{hPipecDencity,jdbcType=REAL}, ",
        "#{hPipedDencity,jdbcType=REAL}, #{hPipeaX,jdbcType=REAL}, ",
        "#{hPipeaY,jdbcType=REAL}, #{hPipeaV,jdbcType=REAL}, #{hPipebX,jdbcType=REAL}, ",
        "#{hPipebY,jdbcType=REAL}, #{hPipebV,jdbcType=REAL}, #{hPipecX,jdbcType=REAL}, ",
        "#{hPipecY,jdbcType=REAL}, #{hPipecV,jdbcType=REAL}, #{hPipedX,jdbcType=REAL}, ",
        "#{hPipedY,jdbcType=REAL}, #{hPipedV,jdbcType=REAL})"
    })
    int insert(CoalPipingHistoryMillD record);

    @InsertProvider(type=CoalPipingHistoryMillDSqlProvider.class, method="insertSelective")
    int insertSelective(CoalPipingHistoryMillD record);

    @Select({
        "select",
        "id, h_coal_mill_id, h_time, h_pipeA_Velocity, h_pipeB_Velocity, h_pipeC_velocity, ",
        "h_pipeD_velocity, h_pipeA_dencity, h_pipeB_dencity, h_pipeC_dencity, h_pipeD_dencity, ",
        "h_pipeA_x, h_pipeA_y, h_pipeA_v, h_pipeB_x, h_pipeB_y, h_pipeB_v, h_pipeC_x, ",
        "h_pipeC_y, h_pipeC_v, h_pipeD_x, h_pipeD_y, h_pipeD_v",
        "from dcoal_piping_history",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="h_coal_mill_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="h_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="h_pipeA_Velocity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeB_Velocity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeC_velocity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeD_velocity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeA_dencity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeB_dencity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeC_dencity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeD_dencity", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeA_x", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeA_y", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeA_v", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeB_x", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeB_y", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeB_v", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeC_x", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeC_y", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeC_v", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeD_x", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeD_y", javaType=Float.class, jdbcType=JdbcType.REAL),
        @Arg(column="h_pipeD_v", javaType=Float.class, jdbcType=JdbcType.REAL)
    })
    CoalPipingHistoryMillD selectByPrimaryKey(Long id);

    @UpdateProvider(type=CoalPipingHistoryMillDSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CoalPipingHistoryMillD record);

    @Update({
        "update dcoal_piping_history",
        "set h_coal_mill_id = #{hCoalMillId,jdbcType=BIGINT},",
          "h_time = #{hTime,jdbcType=TIMESTAMP},",
          "h_pipeA_Velocity = #{hPipeaVelocity,jdbcType=REAL},",
          "h_pipeB_Velocity = #{hPipebVelocity,jdbcType=REAL},",
          "h_pipeC_velocity = #{hPipecVelocity,jdbcType=REAL},",
          "h_pipeD_velocity = #{hPipedVelocity,jdbcType=REAL},",
          "h_pipeA_dencity = #{hPipeaDencity,jdbcType=REAL},",
          "h_pipeB_dencity = #{hPipebDencity,jdbcType=REAL},",
          "h_pipeC_dencity = #{hPipecDencity,jdbcType=REAL},",
          "h_pipeD_dencity = #{hPipedDencity,jdbcType=REAL},",
          "h_pipeA_x = #{hPipeaX,jdbcType=REAL},",
          "h_pipeA_y = #{hPipeaY,jdbcType=REAL},",
          "h_pipeA_v = #{hPipeaV,jdbcType=REAL},",
          "h_pipeB_x = #{hPipebX,jdbcType=REAL},",
          "h_pipeB_y = #{hPipebY,jdbcType=REAL},",
          "h_pipeB_v = #{hPipebV,jdbcType=REAL},",
          "h_pipeC_x = #{hPipecX,jdbcType=REAL},",
          "h_pipeC_y = #{hPipecY,jdbcType=REAL},",
          "h_pipeC_v = #{hPipecV,jdbcType=REAL},",
          "h_pipeD_x = #{hPipedX,jdbcType=REAL},",
          "h_pipeD_y = #{hPipedY,jdbcType=REAL},",
          "h_pipeD_v = #{hPipedV,jdbcType=REAL}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CoalPipingHistoryMillD record);


    @Select({"select",
            "id, h_coal_mill_id, h_time, h_pipeA_Velocity, h_pipeB_Velocity, h_pipeC_velocity, ",
            "h_pipeD_velocity, h_pipeA_dencity, h_pipeB_dencity, h_pipeC_dencity, h_pipeD_dencity, ",
            "h_pipeA_x, h_pipeA_y, h_pipeA_v, h_pipeB_x, h_pipeB_y, h_pipeB_v, h_pipeC_x, ",
            "h_pipeC_y, h_pipeC_v, h_pipeD_x, h_pipeD_y, h_pipeD_v",
            "from dcoal_piping_history",
            "where h_time between #{0,jdbcType=TIMESTAMP} and #{1,jdbcType=TIMESTAMP}"})
    List<CoalPipingHistoryMillD> findListBetweenTime(Date begin, Date end);
}