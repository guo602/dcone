 package com.dcone.dtss;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

@Controller
public class BalanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/balance_add", method=RequestMethod.GET)
	public String balanceAdd() {
		return "balance_add";
	}
	@RequestMapping(value="/balance_adding")
	public String balanceAdding(String itcode,String username, String amount ,Locale locale,  Model model) {
		logger.info("itcode:" +itcode +"username:"+username + " 充值 "+ amount);
		logger.info(jdbcTemplate.toString());
		int i = WalletDAO.balance_add(itcode, username, amount, locale, jdbcTemplate);
		String result="";
		if(i == 1) {
			result = "充值成功";
		} else if(i == -1) {
			result = "用户信息填写错误!";
		}else {
			result = "充值失败,请稍后再试!";
		}
		model.addAttribute("result",result);
		return "balance_add_result";
	}
	
	
}
