package com.dcone.dtss.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class WalletDAO {
	
	/**
	 * 用户帐户充值
	 * @param itcode 充值的员工号
	 * @param username 充值的员工姓名
	 * @param amount   充值的金额
	 * @param locale   时间区域
	 * @param jdbcTemplate  spring jdbcTemplate 对象
	 * @return 1,成功;-1,用户不存在;0,其他错误.
	 */
	public static int balance_add(String itcode,String username, String amount ,Locale locale, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode=? and username=?", user_mapper, new Object[] {itcode, username});
			
			RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
			dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where uid  = ?", wallet_mapper, user.getUid());
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String formattedDate = dateFormat.format(date);
			int i = jdbcTemplate.update("insert into dc_trade values(null, ?,?,?);", new Object[] {wallet.getWid(),amount,formattedDate});
			if(i>0) {
				int j = jdbcTemplate.update("update dc_wallet set amount = amount + ? where wid=?", new Object[]{amount,wallet.getWid()});
				if(j>0) {
					return 1;
				}
			}
			
			
		}catch(Exception e) {
			return -1;
		}
		
		return 0;
	}

	
	public static dc_wallet getWalletById(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);

		dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where uid  = ?", wallet_mapper, uid);
		
		
		return wallet;
	}
	
	public static dc_wallet getWalletByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode=?  ", user_mapper, new Object[] {itcode});
		int uid=user.getUid();
		
		return getWalletById(  uid,     jdbcTemplate);
	}
	
//	public static boolean initWallet(int uid, JdbcTemplate jdbcTemplate) {
//		if(getWalletById(  uid,     jdbcTemplate).getinit())return true;
//		else return false;
//	}
//	
//	public static boolean initWallet(String itcode, JdbcTemplate jdbcTemplate) {
//		if(getWalletByItcode(  itcode,     jdbcTemplate).getinit())return true;
//		else return false;
//		
//		
//	}
	
	public static List<dc_wallet> getAllWallets( JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);

		List<dc_wallet>   dc_w= jdbcTemplate.query("select * from dc_wallet ", wallet_mapper);
		
		
		
		return dc_w;
	}
	
	public static int balance_getRain(int wid,int luckynum,JdbcTemplate jdbcTemplate) {
 
		int j = jdbcTemplate.update("update dc_wallet set amount = amount + ? where wid=?", new Object[]{luckynum,wid});
		return j;
		
	}
}
