package com.dcone.dtss.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class TradeDAO {

	public static List<dc_trade> getTradesByUid(int uid, JdbcTemplate jdbcTemplate) {
		int wid=WalletDAO.getWalletById(uid, jdbcTemplate).getWid();
		RowMapper<dc_trade>  t_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);

		List<dc_trade>   dc_t= jdbcTemplate.query("select * from dc_wallet where wid=?", t_mapper,new Object[] {wid});
		
		return dc_t;
	}
	public static List<dc_trade> getTradesByUid(int uid, String start, String end, JdbcTemplate jdbcTemplate) {
		
		
		int wid=WalletDAO.getWalletById(uid, jdbcTemplate).getWid();
		RowMapper<dc_trade>  t_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);

		List<dc_trade>   dc_t= jdbcTemplate.query("select * from dc_wallet where wid=?and tradetime<?and tradetime >?", t_mapper,new Object[] {wid,start,end});
		
		return dc_t;
	}
	
	public static List<dc_trade> getTradesByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode,  jdbcTemplate);
		
		return getTradesByUid(user.getUid(),   jdbcTemplate);
	}
	
	public static List<dc_trade> getTradesByUser(dc_user user, JdbcTemplate jdbcTemplate) {
		return getTradesByUid(user.getUid(),   jdbcTemplate);
	}
	
	public static dc_trade getTradeByTid(int id, JdbcTemplate jdbcTemplate) {
		return null;
	}
	/**
	 * 判定交易是否能够进行
	 * @return
	 */
	
	private static boolean preTrade(int wid,int amount, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);

		dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where wid  = ?", wallet_mapper, wid);
		if(wallet.getAmount()<amount)return false;
		else return true;
	}
	
	public static boolean createTrade(int wid, int amount, String date , String memo, JdbcTemplate jdbcTemplate) {
		if(preTrade(wid,amount,jdbcTemplate) ) {
			RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);

			dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where wid  = ?", wallet_mapper, wid);
			
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
			String formattedDate = dateFormat.format(date);
			int i = jdbcTemplate.update("insert into dc_trade values(null, ?,?,?);", new Object[] {wallet.getWid(),amount,formattedDate});

			
			
			return true;//写入交易数据
		}
		return false;
	}
	
	
	
	
	
	
	
	
}
