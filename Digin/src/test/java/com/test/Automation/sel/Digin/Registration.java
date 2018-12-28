package com.test.Automation.sel.Digin;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.Automation.sel.Digin.Action.RegUser;
import com.test.Automation.sel.Digin.testBase.TestBase;

public class Registration extends TestBase {
	
	RegUser rUser;
	@BeforeTest
	public void setup(){
		init();	
	}
	@Test
	public void registration(){
		try{
		 rUser=new RegUser(driver);
		rUser.registerForm("kum", "a", "ran", "kum02@gmail.com", "Test@123", "Test@123");
		Assert.assertEquals(rUser.loginPage(), "GET A QUOTE");
	}
	catch(Exception e){
		e.getStackTrace();
	}
	}
	@AfterTests
	public void end(){
		driver.close();
	}

}
