package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Positive {
	
	
	WebDriver driver;
	Faker faker = new Faker();
	
	@BeforeMethod
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("http://automationpractice.com");
	}
	
//	test scenario: login test
//	go to http://automationpractice.com
//	register a new user. You have to generate a new email, first name, last name every time
//	Sign out once the registration is complete
//	Log back using the same information
//	Verify that correct first name and last name is displayed on the top right


	@Test
	public void loginTest() throws InterruptedException {
	driver.findElement(By.cssSelector("a[href='http://automationpractice.com/index.php?controller=my-account']"))
	.click();
	
	String email = faker.internet().emailAddress();
    driver.findElement(By.xpath("(//input[@class='is_required validate account_input form-control'])[1]")).sendKeys(email+Keys.ENTER);
  
    //String FirstName = faker.name().firstName();
   // driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(faker.name().firstName());
     Thread.sleep(2000);
     
       String FirstName = faker.name().firstName();
       driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
      
       Thread.sleep(2000);
       
       String LastName = faker.name().lastName();
       driver.findElement(By.id("customer_lastname")).sendKeys(LastName);
    
       driver.findElement(By.id("email")).clear();
     
       driver.findElement(By.id("email")).sendKeys(email);
       
       String password = faker.internet().password();
       driver.findElement(By.id("passwd")).sendKeys(password);
       
       String address = faker.address().buildingNumber();
       driver.findElement(By.id("address1")).sendKeys(address);
       
       String city = faker.address().city();
       driver.findElement(By.id("city")).sendKeys(city);
       
       String state = faker.address().state();
       driver.findElement(By.id("id_state")).sendKeys(state);
       
       String zipcode = "22102";
       driver.findElement(By.id("postcode")).sendKeys("22102");;
       
       String Country = faker.address().country();
       driver.findElement(By.id("id_country"));
       
       String mobilephone = "7038706125";
       driver.findElement(By.id("phone_mobile")).sendKeys("7038706125");
       
       driver.findElement(By.id("submitAccount")).click();;
     
       driver.findElement(By.className("logout")).click();
       
       driver.findElement(By.id("email")).sendKeys(email);
       driver.findElement(By.id("passwd")).sendKeys(password+Keys.ENTER);
       
      String actual=driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=my-account']/span")).getText();
      String expected=FirstName+" "+LastName;
      Assert.assertEquals(actual,expected);
     
      driver.close();
	}
}