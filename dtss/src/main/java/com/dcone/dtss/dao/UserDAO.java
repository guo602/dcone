package com.dcone.dtss.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class UserDAO {
	/**
	 * 通过UID获取用户对象
	 * @author jszhang
	 * @param uid 用户id
	 * @return 指定ID的用户dc_user对象
	 */
	public static dc_user getUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where uid=?  ", user_mapper, new Object[] {uid});
		
		
		return user;
	}
	/**
	 * 通过Itcode获取用户对象
	 * @param itcode
	 * @param jdbcTemplate
	 * @return
	 */
	public static dc_user getUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode=?  ", user_mapper, new Object[] {itcode});
		
		
		return user;
	}
	
	public static int createUser(String itcode, String username, JdbcTemplate jdbcTemplate) {
		int i = jdbcTemplate.update("insert into dc_user values(null, ?,?,?);", new Object[] {itcode,username,0});
		return i;
	}
	
	public static boolean checkUserInfo(String itcode, String username, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode=? and username=? ", user_mapper, new Object[] {itcode,username});
		if(user==null)
		
		return false;
		
		else return true;
	}
	/**
	 * 修改数据库,修改model,完成用户锁定
	 * @param uid
	 * @return
	 */
	public static boolean lockUserById(int uid, JdbcTemplate jdbcTemplate) {
		String sql="update dc_wallet set lock=? where uid=?";
		 jdbcTemplate.update(sql,new Object[]{true,uid});
		
		return true;
	}
	/**
	 * 
	 * @param itcode
	 * @return
	 */
	public static boolean lockUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		
		int uid= getUserByItcode( itcode,  jdbcTemplate).getUid();
		
		String sql="update dc_wallet set lock=? where uid=?";
		 jdbcTemplate.update(sql,new Object[]{true,uid});
		
		return true;
	}
	
	public static boolean unlockUserByID(int id, JdbcTemplate jdbcTemplate) {
		String sql="update dc_wallet set lock=? where uid=?";
		 jdbcTemplate.update(sql,new Object[]{false,id});
		return true;
	}
	
   /**
    * 检查是否被锁住
    * @param uid
    * @param jdbcTemplate
    * @return
    */
//	public static boolean isLock(int uid, JdbcTemplate jdbcTemplate) {
//		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
//
//		dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where uid  = ?", wallet_mapper, uid);
//		return wallet.getlock();
//	}
	
	
}
