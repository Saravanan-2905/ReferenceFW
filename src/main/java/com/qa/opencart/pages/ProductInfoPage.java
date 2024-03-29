package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	protected By productHeader = By.cssSelector("div#content h1");
	protected By productImages = By.cssSelector("ul.thumbnails img");
	protected By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	protected By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap = new HashMap<String, String>();

	//page constructor
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getProductHeaderName()
	{
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header: " +productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product " +getProductHeaderName() + "images count : " +imagesCount);
		return imagesCount;
	}
	
	private void getProductMetaData()
	{
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		for(WebElement e : metaDataList)
		{
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaVal = metaData.split(":")[1].trim();
			
			productMap.put(metaKey, metaVal);
		}
		
	}
		
	private void getProductPriceData()
	{
		List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String productPrice = metaPriceList.get(0).getText();
		String productExTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim();
		
		productMap.put("price", productPrice);
		productMap.put("extaxprice", productExTaxPrice);
				
	}
	
	public  Map<String, String> getProductDetails()
	{
		productMap.put("productname", getProductHeaderName());
		
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productMap);
		
		return productMap;
		
	}
		
	
	
	

}
