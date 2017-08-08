package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_wallet;
import com.dcone.dtss.model.red_record_view;

public class RedRecordViewDAO {
	public static List<red_record_view> getAllRecords( JdbcTemplate jdbcTemplate) {
		RowMapper<red_record_view> view = new BeanPropertyRowMapper<red_record_view>(red_record_view.class);

		List<red_record_view>   record_view= jdbcTemplate.query("select * from red_record_view ", view);
		
		
		
		return record_view;
	}
	
	
}
