package com.nw.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.nw.model.CoalPipingHistoryMillC;

public class CoalPipingHistoryMillCSqlProvider {

    public String insertSelective(CoalPipingHistoryMillC record) {
        BEGIN();
        INSERT_INTO("ccoal_piping_history");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.gethCoalMillId() != null) {
            VALUES("h_coal_mill_id", "#{hCoalMillId,jdbcType=BIGINT}");
        }
        
        if (record.gethTime() != null) {
            VALUES("h_time", "#{hTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.gethPipeaVelocity() != null) {
            VALUES("h_pipeA_Velocity", "#{hPipeaVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipebVelocity() != null) {
            VALUES("h_pipeB_Velocity", "#{hPipebVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipecVelocity() != null) {
            VALUES("h_pipeC_velocity", "#{hPipecVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipedVelocity() != null) {
            VALUES("h_pipeD_velocity", "#{hPipedVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipeaDencity() != null) {
            VALUES("h_pipeA_dencity", "#{hPipeaDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipebDencity() != null) {
            VALUES("h_pipeB_dencity", "#{hPipebDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipecDencity() != null) {
            VALUES("h_pipeC_dencity", "#{hPipecDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipedDencity() != null) {
            VALUES("h_pipeD_dencity", "#{hPipedDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipeaX() != null) {
            VALUES("h_pipeA_x", "#{hPipeaX,jdbcType=REAL}");
        }
        
        if (record.gethPipeaY() != null) {
            VALUES("h_pipeA_y", "#{hPipeaY,jdbcType=REAL}");
        }
        
        if (record.gethPipeaV() != null) {
            VALUES("h_pipeA_v", "#{hPipeaV,jdbcType=REAL}");
        }
        
        if (record.gethPipebX() != null) {
            VALUES("h_pipeB_x", "#{hPipebX,jdbcType=REAL}");
        }
        
        if (record.gethPipebY() != null) {
            VALUES("h_pipeB_y", "#{hPipebY,jdbcType=REAL}");
        }
        
        if (record.gethPipebV() != null) {
            VALUES("h_pipeB_v", "#{hPipebV,jdbcType=REAL}");
        }
        
        if (record.gethPipecX() != null) {
            VALUES("h_pipeC_x", "#{hPipecX,jdbcType=REAL}");
        }
        
        if (record.gethPipecY() != null) {
            VALUES("h_pipeC_y", "#{hPipecY,jdbcType=REAL}");
        }
        
        if (record.gethPipecV() != null) {
            VALUES("h_pipeC_v", "#{hPipecV,jdbcType=REAL}");
        }
        
        if (record.gethPipedX() != null) {
            VALUES("h_pipeD_x", "#{hPipedX,jdbcType=REAL}");
        }
        
        if (record.gethPipedY() != null) {
            VALUES("h_pipeD_y", "#{hPipedY,jdbcType=REAL}");
        }
        
        if (record.gethPipedV() != null) {
            VALUES("h_pipeD_v", "#{hPipedV,jdbcType=REAL}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(CoalPipingHistoryMillC record) {
        BEGIN();
        UPDATE("ccoal_piping_history");
        
        if (record.gethCoalMillId() != null) {
            SET("h_coal_mill_id = #{hCoalMillId,jdbcType=BIGINT}");
        }
        
        if (record.gethTime() != null) {
            SET("h_time = #{hTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.gethPipeaVelocity() != null) {
            SET("h_pipeA_Velocity = #{hPipeaVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipebVelocity() != null) {
            SET("h_pipeB_Velocity = #{hPipebVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipecVelocity() != null) {
            SET("h_pipeC_velocity = #{hPipecVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipedVelocity() != null) {
            SET("h_pipeD_velocity = #{hPipedVelocity,jdbcType=REAL}");
        }
        
        if (record.gethPipeaDencity() != null) {
            SET("h_pipeA_dencity = #{hPipeaDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipebDencity() != null) {
            SET("h_pipeB_dencity = #{hPipebDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipecDencity() != null) {
            SET("h_pipeC_dencity = #{hPipecDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipedDencity() != null) {
            SET("h_pipeD_dencity = #{hPipedDencity,jdbcType=REAL}");
        }
        
        if (record.gethPipeaX() != null) {
            SET("h_pipeA_x = #{hPipeaX,jdbcType=REAL}");
        }
        
        if (record.gethPipeaY() != null) {
            SET("h_pipeA_y = #{hPipeaY,jdbcType=REAL}");
        }
        
        if (record.gethPipeaV() != null) {
            SET("h_pipeA_v = #{hPipeaV,jdbcType=REAL}");
        }
        
        if (record.gethPipebX() != null) {
            SET("h_pipeB_x = #{hPipebX,jdbcType=REAL}");
        }
        
        if (record.gethPipebY() != null) {
            SET("h_pipeB_y = #{hPipebY,jdbcType=REAL}");
        }
        
        if (record.gethPipebV() != null) {
            SET("h_pipeB_v = #{hPipebV,jdbcType=REAL}");
        }
        
        if (record.gethPipecX() != null) {
            SET("h_pipeC_x = #{hPipecX,jdbcType=REAL}");
        }
        
        if (record.gethPipecY() != null) {
            SET("h_pipeC_y = #{hPipecY,jdbcType=REAL}");
        }
        
        if (record.gethPipecV() != null) {
            SET("h_pipeC_v = #{hPipecV,jdbcType=REAL}");
        }
        
        if (record.gethPipedX() != null) {
            SET("h_pipeD_x = #{hPipedX,jdbcType=REAL}");
        }
        
        if (record.gethPipedY() != null) {
            SET("h_pipeD_y = #{hPipedY,jdbcType=REAL}");
        }
        
        if (record.gethPipedV() != null) {
            SET("h_pipeD_v = #{hPipedV,jdbcType=REAL}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}