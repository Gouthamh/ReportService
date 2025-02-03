package test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.mongodb.internal.connection.Time;

import groovy.time.TimeDuration;

import java.util.Timer;

import org.testng.*;

public class CustomTestListener implements ITestListener ,ISuiteListener{

     ExtentReports extent;
     ExtentTest test ;
    @Override
    public void onStart(ITestContext context) {
    	String classname = this.getClass().getSimpleName();
        String reportFilePath = Time.nanoTime()+"_extent-report1.html";
	     extent = new ExtentReports();

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath)
        		.viewConfigurer()
    		    .viewOrder()
    		    .as(new ViewName[] { 
    			   ViewName.DASHBOARD, 
    			   ViewName.TEST, 
    			   ViewName.AUTHOR, 
    			   ViewName.DEVICE, 
    			   ViewName.EXCEPTION, 
    			   ViewName.LOG 
    			})
    		  .apply();  
     
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       
    	test.pass(result.getName().toUpperCase());
    	
        System.out.println("Test Passed: " + result.getName());
        // Output green color
        System.out.println("\u001B[32m" + "Pass" + "\u001B[0m");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	test.fail(result.getName());
    	System.out.println("Test Failed: " + result.getName());
        // Output red color
        System.out.println("\u001B[31m" + "Fail" + "\u001B[0m");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	test.skip(result.getName());
    	System.out.println("Test Skipped: " + result.getName());
        // Output yellow color
        System.out.println("\u001B[33m" + "Skip" + "\u001B[0m");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Implement other methods as needed, such as onTestFailedButWithinSuccessPercentage
}
