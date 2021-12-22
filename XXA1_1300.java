package com.ck.qa.selenium.csr.cb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.ck.common.util.DATE;
import com.ck.qa.selenium.utils.SeleniumCSR;

public class XXA1_1300 {
	
	private final static Logger log = Logger.getLogger(XXA1_1300.class);
	
	private int success_VP, fail_VP;
	
	public void execute(WebDriver driver) throws InterruptedException{
		
		SeleniumCSR utils = new SeleniumCSR(driver);
		
		utils.clickSysMenu("Accident insurance System");	
		utils.clickMenu("New Contract","Travel");
				
		utils.keyinByID("QINSD_ID", "A100000001");
		utils.clickByName("btn_query");	
		try{
			driver.switchTo().alert().accept();
		} catch(NoAlertPresentException nape){
			utils.clickByName("btn_delete");
			utils.keyinByID("QINSD_ID", "A100000001");
		}

		utils.clickByName("btn_query_fetch1");	
		utils.keyinByID("INSD_ADDR", "Sunset Blvd, Beverly Hills");
		utils.keyinByID("INSD_EMAIL", "xxxx@ckcompany.com.tw");
		utils.keyinByID("table1_BENE_NAME00", "Emily Chen");
		utils.keyinByID("table1_BENE_ID00", "H100000008");
		utils.keyinByID("TOT_HD_AMT", "100");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");		
		String nextROCDate = DATE.toROCDate(DATE.addDate(df.format(DATE.today()), 0, 0, 1));
		utils.keyinByID("ISSUE_DATE", nextROCDate);		
		
		utils.keyinByID("BGN_TIME_HH", "01");
		utils.keyinByID("ISSUE_DAYS", "1");
		utils.keyinByID("table0_AGNT_LICENCE00", "0080200900");
		utils.clickByName("btn_insert");
		utils.acceptAlert();
		utils.acceptAlert();
		
		String CAL_TOT_PREM_value = utils.getValueByID("CAL_TOT_PREM");
		utils.keyinByID("TOT_PREM", CAL_TOT_PREM_value);

		utils.clickByName("btn_insert");
		utils.acceptAlert();
		
		if("Insert Success".equals(utils.getBottomMsg())){
			success_VP++;
		} else{
			fail_VP++;
		}	
		
		log.info("Success vegify point : " + success_VP);
		log.info("Fail vegify point : " + fail_VP);
	}
}