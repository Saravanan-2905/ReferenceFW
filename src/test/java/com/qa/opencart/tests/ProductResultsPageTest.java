package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][]
		{
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	@DataProvider
	public Object[][] getSearchExcelTestData()
	{
		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
	}
	
	
	
	
	@Test(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchKey, String productName, String imageCount)
	{
		serachResultsPage = accPage.doSearch(searchKey);
		productInfoPage = serachResultsPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()), imageCount);
	}
	
	@Test
	public void productInfoTest()
	{
		serachResultsPage = accPage.doSearch("MacBook");
		productInfoPage = serachResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		
		//Assert --> Hard Assertion, Assert inside the all methods are static methods. If test's fail terminate the particular test method. Used for Single Check
		
		//SoftAssert --> Soft Assertion, Have to create an Objects. If test's fail rest of the asserts also tested. Mandatory to write assertAll() at the end. Used for Multiple Check
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$2,000.00");
		
		softAssert.assertAll();
	}

}
