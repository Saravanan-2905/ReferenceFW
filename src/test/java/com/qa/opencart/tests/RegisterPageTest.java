package com.qa.opencart.tests;

//import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetUp()
	{
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailID()
	{
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
	  //return "testautomation" + UUID.randomUUID() + "@opencart.com";
	}
	
//	@DataProvider
//	public Object[][] getUserRegData()
//	{
//		return new Object[][]
//		{
//			{"Udhya", "Kumar", "9943980764", "hello@123", "yes"},
//			{"Velan", "Raj", "9043980764", "hello@123", "no"},
//			{"Kapoor", "Khan", "8543980764", "hello@123", "yes"},
//		};
//	}
	
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() 
	{
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		
		return regData;
	}
	
	
	//run = total rows
	//parameter = total columns
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe)
	{
		boolean isRegDone = registerPage.userRegisteration(firstName, lastName, getRandomEmailID(), telephone, password, subscribe);
		
		Assert.assertTrue(isRegDone);
	}

}
