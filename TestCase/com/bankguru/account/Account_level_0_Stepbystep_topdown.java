package com.bankguru.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_level_0_Stepbystep_topdown {
	WebDriver driver;
	String loginPageURL, userIDInfor, passIDInfor, email;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		email = "selenium" + ramdomNumber() + "@gmail.com";

	}

	@Test
	public void TC_0_registerToSystem() {
		// verrify trang Login
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		// Click to Here Link
		loginPageURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text() ='here']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@name ='emailid']")).isDisplayed());
		// Input email to Email Textbox
		driver.findElement(By.xpath("//input[@name ='emailid']")).sendKeys(email);
		// Click to SUBMIT button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// get User and Pass
		// Tao biến để hứng dữ liệu hàm getText() trả ra String
		userIDInfor = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passIDInfor = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_0_loginToSystem() {
		driver.get(loginPageURL);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name ='emailid']")).isDisplayed());
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIDInfor);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passIDInfor);
		// Click to Login Btn
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// verify Home page
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		// check UserIDInfor Display in HomePage
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userIDInfor + " ']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int ramdomNumber() {
		Random random = new Random();
		return random.nextInt(999999);

	}

}
