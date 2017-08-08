package MyRainThread;

import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dcone.dtss.dao.RedPacketDao;
import com.dcone.dtss.dao.RedRecordDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.dc_wallet;

public class MyRainThread extends Thread{
	boolean flag = false;
	JdbcTemplate template;
	int round ;
	public MyRainThread(){
		//test
		
		//test guo
		round=0;
	}
	
	public MyRainThread(boolean flag,JdbcTemplate template,int round){
		this.flag=flag;
		this.round=round;
		this.template=template;
		
		
	}
	
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
    /**
     * 快跑！
     */
	public void run() {
		System.out.println("雷公助我");
	    List<dc_wallet> wallets=	WalletDAO.getAllWallets(template);
	
		System.out.println("1");

	    int num=wallets.size();
		Random n=new Random();
		Random money=new Random();
		System.out.println("2");
		
		if(round<3) {
        	//round++;
        	
        	int total=RedPacketDao.getTotalByRound(round,template);
    		System.out.println(total);
          
        	
        	int luckynum;
        	int luckyman;
        	while(total>0) {
        		luckyman=n.nextInt(num);
        		
        		if(total>5000) {
        		luckynum=money.nextInt(5001);
        		}
        		else luckynum=total;
        		
        		
        		total-=luckynum;
                
        		//System.out.println(luckynum+" "+luckyman+" ");
        		
        		RedRecordDAO.createRedSend(wallets.get(luckyman).getWid(), luckynum, round, template);
        		
        		
        		
        	}
        	
        	
        }
        else {
        	System.out.println("雨停了");
        	return;
		
        }
		
		
	}
	
	
	
	
	
	
	
}
