package com.test.Automation.sel.Digin;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.Automation.sel.Digin.Action.RegUser;
import com.test.Automation.sel.Digin.testBase.TestBase;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class Verifylogin extends TestBase{
	ATUTestRecorder recorder;
	RegUser rUser;
	@DataProvider(name = "loginData")
	public String[][] getTestData(){
		String[][] testRecored = getData("TestData.xlsx","Sheet1");
		return testRecored;
	}
	
	@BeforeTest
	public void setUp() throws ATUTestRecorderException {
		recorder = new ATUTestRecorder("C:\\Users\\WMS\\Desktop\\Recorder","testvideo",false);
		recorder.start();
		init();
	}
	
	@Test(dataProvider = "loginData")
	public void getLoginData(String Uname, String Pword, String runMode){
		try{
			if(runMode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this method as no");
			}
		rUser = new RegUser(driver);
		rUser.verfyLoginPage(Uname, Pword);
		boolean Status = rUser.logOutPage();
		getScreenshot("testlog" +Uname);
		if(Status){
			rUser.logout();
		}
		Assert.assertEquals(Status, true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@AfterTest
	public void close() throws ATUTestRecorderException{
		driver.close();
		recorder.stop();
	}
	

}
