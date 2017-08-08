package com.dcone.dtss;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.RedPacketDao;
import MyRainThread.MyRainThread;
@Controller
public class RedPacketController {
	private static final Logger logger = LoggerFactory.getLogger(RedPacketController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/init_red", method=RequestMethod.GET)
	public String balanceAdd() {
		return "init_red";
	}
	
	@RequestMapping(value="/preredrain", method=RequestMethod.GET)
	public String redRain() {
		
		
		return "red_rain";
	}
	
	@RequestMapping(value="/TimeToRain", method=RequestMethod.GET)
	public String Rainning() {
		
		MyRainThread t=new MyRainThread();
		t.setTemplate(jdbcTemplate);
		t.start();
		return "rainning";
	}
	
	@RequestMapping(value="/initing_red")
	public String balanceAdding(String mima,Locale locale,  Model model) {
	   if(mima.equals("zyqdmw")) {
		
		   RedPacketDao.InitRed(1,2000000,jdbcTemplate);
		   RedPacketDao.InitRed(2,2500000,jdbcTemplate);
		   RedPacketDao.InitRed(3,2000000,jdbcTemplate);
		   
		   
		return "init_success";
		
	   }
	   
	   else return "home";
	}
	
	}
	
	

