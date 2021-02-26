package com.NordicNaturals.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.NordicNaturals.helper.TestBase;
import com.NordicNaturals.helper.TestUtilities;

public class TC_001_HomePageLinkValidationTest extends TestBase {

	TestBase testBase;

	// constructor to call Parent constructor

	public TC_001_HomePageLinkValidationTest() {
		super();
	}

	@Parameters({ "Browser", "URL" })
	@BeforeMethod
	public void startSession(String browser, String url) {
		System.out.println("LOG:INFO- Setting up the Browser " + browser + " " + url);
		driver = TestBase.startApplication(browser, url);
		System.out.println("LOG:INFO- Browser and Application is up and running");
	}

	@Test
	public void Test_HomePage_LinksSatus() {

		String validation_message = TestUtilities.linkValidation("https://www.nordicnaturals.com/consumers/");
		Assert.assertEquals("Success", validation_message);
	}

	// It will execute after every test execution
	@AfterMethod
	public void tearDown(ITestResult result) {

		// Here will compare if test is failing then only it will enter into if
		// condition
		if (ITestResult.FAILURE == result.getStatus()) {
			TestBase.captureScreenshot(driver, "ScreenShot");
		}

		// close application
		driver.quit();
	}

}
