package com.ck.qa.selenium.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCXR {
	
	private final static Logger log = Logger.getLogger(SeleniumCXR.class);

	private WebDriver driver;

	public SeleniumCXR(WebDriver webdriver) {
		driver = webdriver;
	}

	public void clickSysMenu(String text) {		
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='leftFrame']")));
		WebElement elem = driver.findElement(By.xpath("//li[text()='"+text+"']"));
		elem.click();
	}

	public void clickMenu(String menuText, String subMenuText) {
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='leftFrame']")));
		driver.findElement(By.xpath("//div[text()='"+menuText+"']")).click();
		driver.findElement(By.xpath("//a[text()='"+subMenuText+"']")).click();
		driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
	}
	

	public String getValueByID(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement elem = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(id)));
		return elem.getAttribute("value");
	}	
	
	public String getBottomMsg() throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='leftFrame']")));
		driver.switchTo().frame(driver.findElement(By.id("bottomFrame")));
		By by = By.id("ck_common_msgBoard");
		String bottomMsg = "";
		int i = 0;
		while(i < 5){
			bottomMsg = driver.findElement(by).getAttribute("innerHTML");
			if(StringUtils.isNotBlank(bottomMsg)){
				break;
			} else{
				Thread.sleep(1000);
				i++;
			}
		}	
		return bottomMsg;
	}
	
	public void keyinByID(String id, String context) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement elem = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id(id)));
		elem.clear();
		elem.sendKeys(context);
	}
	
	private void click(String id, String name) {
		By by = null;
		if (StringUtils.isNotBlank(id)) {
			by = By.id(id);
		} else {
			by = By.name(name);
		}

		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement elem = wait.until(ExpectedConditions
				.visibilityOfElementLocated(by));
		elem.click();
	}
	
	public void clickByID(String id) {
		click(id, null);
	}

	public void clickByName(String name) {
		click(null, name);
	}

	public void acceptAlert() throws InterruptedException {
		int i = 0;
		Alert alert = null;
		while (i++ < 60) {
			try {
				alert = driver.switchTo().alert();
				log.error(" Alert Message: " + alert.getText());
				break;
			} catch (NoAlertPresentException e) {
				Thread.sleep(1000);
				log.info("wait alert for " + i * 1000);
				continue;
			}
		}
		alert.accept();
	}
}