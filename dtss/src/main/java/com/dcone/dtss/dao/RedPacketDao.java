package com.dcone.dtss.dao;

import java.text.DateFormat;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_wallet;
import com.dcone.dtss.model.lucky_money;

public class RedPacketDao {

	public static boolean InitRed(int round, int total,  JdbcTemplate jdbcTemplate) {
//			RowMapper<lucky_money> lucky_money_mapper = new BeanPropertyRowMapper<lucky_money>(lucky_money.class);
//
//			lucky_money l_money  = jdbcTemplate.queryForObject("select * from lucky_money where round  = ?", lucky_money_mapper, wid);
			
		
		jdbcTemplate.update("insert into lucky_money values(null,?,?)", new Object[] {round,total});

		//	System.out.print(i);
			
			return true;//写入交易数据
		
	}
	
	public static int getTotalByRound(int round  ,  JdbcTemplate jdbcTemplate) {
		RowMapper<lucky_money> lucky_mapper = new BeanPropertyRowMapper<lucky_money>(lucky_money.class);

		lucky_money lucky  = jdbcTemplate.queryForObject("select * from lucky_money where round  = ?", lucky_mapper, round);
		
		return lucky.getTotal();
		
	}
	
	
	
}
