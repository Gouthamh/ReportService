package com.tecnotree.automatiom.listerns;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.tecnotree.automatiom.Methods.Method;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static final Logger logger = LogManager.getFormatterLogger(RetryAnalyzer.class);

	int counter = 0;
	int retryLimit = 4;
	/*
	 * (non-Javadoc)
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun.
	 * TestNg will call this method every time a test fails. So we 
	 * can put some code in here to decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried
	 * and false it not.
	 *
	 */

	@Override
	public boolean retry(ITestResult result) {

		if(counter < retryLimit)
		{
			counter++;
            try {
				Thread.sleep(Duration.ofSeconds(100));
				System.out.println("========================================================");
				System.out.println("========="+counter+"====================================");
				System.out.println("========================================================");

				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}
}