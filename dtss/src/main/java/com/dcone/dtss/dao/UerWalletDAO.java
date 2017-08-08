package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_user_wallet;
import com.dcone.dtss.model.dc_wallet;

public class UerWalletDAO {
	public dc_user_wallet getWallInfoByUser(dc_user user,JdbcTemplate jdbcTemplate) {
		return getWallInfoByUser(user,jdbcTemplate);
	}
	
	public dc_user_wallet getWallInfoByUid(int uid,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByUid(uid,jdbcTemplate);
		return getWallInfoByUser(user,jdbcTemplate);
	}
	public dc_user_wallet getWallInfoByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode,jdbcTemplate);
		return getWallInfoByUser(user,jdbcTemplate);
	}
	/**
	 * ·µ»ØËùÓÐdc_user_wallet
	 * @param jdbcTemplate
	 * @return
	 */
	public List<dc_user_wallet> getAllWallInfoByUser(JdbcTemplate jdbcTemplate) {
		
		RowMapper<dc_user_wallet> u_w_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);

		List<dc_user_wallet>   dc_u_w= jdbcTemplate.query("select * from dc_user_wallet ", u_w_mapper);
		
		
		return dc_u_w;
	}
}
