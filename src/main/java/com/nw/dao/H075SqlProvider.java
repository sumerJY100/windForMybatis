package com.nw.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.nw.model.H075;

public class H075SqlProvider {

    public String insertSelective(H075 record) {
        BEGIN();
        INSERT_INTO("h_075");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getvTime() != null) {
            VALUES("v_time", "#{vTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getV() != null) {
            VALUES("v", "#{v,jdbcType=REAL}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(H075 record) {
        BEGIN();
        UPDATE("h_075");
        
        if (record.getvTime() != null) {
            SET("v_time = #{vTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getV() != null) {
            SET("v = #{v,jdbcType=REAL}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}