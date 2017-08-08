package com.dcone.dtss.dao;

import java.text.DateFormat;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_wallet;

public class RedRecordDAO {

	public static boolean createRedSend(int wid, int luckynumber, int round, JdbcTemplate jdbcTemplate) {
 			RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);

			dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where wid  = ?", wallet_mapper, wid);
			
			int i = jdbcTemplate.update("insert into lucky_money_record values(null, ?,?,?);", new Object[] {wid,luckynumber,round});
            
			System.out.println(luckynumber);
			
			WalletDAO.balance_getRain(wid,luckynumber,jdbcTemplate);
			
			return true;//写入交易数据
		}
	 
	
}
