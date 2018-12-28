package com.test.Automation.sel.Digin.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.Automation.sel.Digin.excelReader.Excel_Reader;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static ExtentReports extent; //for extent report variable
	public static ExtentTest test;  //for extent report variable
	
	public WebDriver driver;
 
	String url = "https://70.35.206.154/";
	Excel_Reader excel;
	String browser="chrome";
	
	//Extent report name and location
	static{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir")+ "/src/main/java/com/test/Automation/sel/Digin/Report/test"+format.format(calendar.getTime())+".html",false);
	}
	
	public void init(){
		selectBrowser(browser);
		getUrl(url);
		String log4jpath = "log4j.properties";
		PropertyConfigurator.configure(log4jpath);
		
	}
	
public void selectBrowser(String browser){
	if(browser == "chrome")
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\Drivers\\chromedriver.exe");
		log.info("Create a browser" +browser);
		driver = new ChromeDriver();
	}
	else if (browser =="firefox"){
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\Drivers\\geckodriver.exe");
		log.info("Create a browser" +browser);
		driver = new ChromeDriver();
	}
}

public void getUrl(String url){
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
}
 public String[][] getData(String excelName,String sheetName){
	 //C:\Users\WMS\workspace\Digin\src\main\java\com\test\Automation\sel\Digin\excelReader\Excel_Reader.java
	 
	 String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\Automation\\sel\\Digin\\data\\" +excelName;
	 excel = new Excel_Reader(path);
	 String[][] data = excel.getDataFromExcel(excelName, sheetName);
	 return data;
 }
 
 public void getScreenshot(String name){
	 
	 Calendar calendar = Calendar.getInstance();
	 SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	 File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 try{
		 String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\com\\test\\Automation\\sel\\Digin\\Screenshot\\";
		 File destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
		 FileUtils.copyFile(scrFile, destFile);
		 Reporter.log("<a href='"+ destFile.getAbsolutePath()+"'> <img scr='"+destFile.getAbsolutePath()+"' height = '100' width = '100'></a>");
	 }catch (IOException e){
		 e.printStackTrace();
	 }
	 
 }
 
 //Extent report result status
 public void getResult(ITestResult result){
	 if(result.getStatus()==ITestResult.SUCCESS){
		 test.log(LogStatus.PASS, result.getName()+ "Test is Pass");
	 }else if(result.getStatus()== ITestResult.SKIP){
		 test.log(LogStatus.SKIP, result.getName()+ "Test is skipped:" + result.getThrowable());
	 }else if(result.getStatus()==ITestResult.FAILURE){
		 test.log(LogStatus.ERROR, result.getName()+ "Test is failur" + result.getThrowable());
	 }else if(result.getStatus()==ITestResult.STARTED){
		 test.log(LogStatus.INFO, result.getName()+"Test is started");
	 }
 }
 
 @AfterMethod()
 public void afterMethod(ITestResult result){
	 getResult(result);

 }
 
 @BeforeMethod()
 public void beforeMethod(Method result){
	 test = extent.startTest(result.getName());
	 test.log(LogStatus.INFO, result.getName() + "test started");
 }
 
 @AfterClass(alwaysRun=true)
 public void endTest(){
	 extent.endTest(test);
	 extent.flush();
 }
 
 
 
}
