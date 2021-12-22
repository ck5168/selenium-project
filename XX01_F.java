package com.ck.qa.selenium.csr.cb;

import org.openqa.selenium.WebDriver;

import com.ck.qa.selenium.utils.SeleniumLogin;

public class XX01_F {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = SeleniumLogin.login_CK();		
		new XXA1_1300().execute(driver);
		
		Thread.sleep(10000);
		driver.close();
	}
}