package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Negative {
	
WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("http://automationpractice.com");
	}

	//test scenario: wrong credentials test
	//go to http://automationpractice.com
	//try to login by entering any wrong email (email must have a correct format) and any password
	//verify that message Authentication failed. is displayed
	
	@Test
	public void wrongCredentials() {

		driver.findElement(By.className("login")).click();
		
		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		driver.findElement(By.id("email")).sendKeys(email);
		String pass = faker.internet().password();
		driver.findElement(By.id("passwd")).sendKeys(pass);
		driver.findElement(By.id("SubmitLogin")).click();
		String expected = "Authentication failed.";
		String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
		Assert.assertEquals(actual, expected);
	}
	
//	test scenario: invalid email test
//	go to http://automationpractice.com 
//	try to login by entering a email with invalid format and any password
//	verify that message Invalid email address. is displayedp
	@Test
	public void invalidEmail() {
		
		driver.findElement(By.className("login")).click();
		
		Faker faker = new Faker();
		String email = faker.company().name();
		driver.findElement(By.id("email")).sendKeys(email);
		String pass = faker.internet().password();
		driver.findElement(By.id("passwd")).sendKeys(pass);
		driver.findElement(By.id("SubmitLogin")).click();
		String expected = "Invalid email address.";
		String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
		Assert.assertEquals(actual, expected);
	}
	
//	test scenario: blank email test
//	go to http://automationpractice.com 
//	try to login by leaving the email field empty and entering any password
//	verify that message An email address required. is displayed
	
	@Test
	public void blankEmail() {
		
	driver.findElement(By.className("login")).click();
		
		driver.findElement(By.id("email")).clear();
		Faker faker = new Faker();
		String pass = faker.internet().password();
		driver.findElement(By.id("passwd")).sendKeys(pass);
		driver.findElement(By.id("SubmitLogin")).click();
		String expected = "An email address required.";
		String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
		Assert.assertEquals(actual, expected);
	}
	
//	test scenario: blank password test
//	go to http://automationpractice.com
//	try to login by entering an email and leaving the password field blank
//	verify that message Password is required. is displayed
	
	@Test
	public void blankPassword() {

		driver.findElement(By.className("login")).click();

		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).clear();
		driver.findElement(By.id("SubmitLogin")).click();
		String expected = "Password is required.";
		String actual = driver.findElement(By.xpath("(//li)[15]")).getText();
		Assert.assertEquals(actual, expected);

	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
