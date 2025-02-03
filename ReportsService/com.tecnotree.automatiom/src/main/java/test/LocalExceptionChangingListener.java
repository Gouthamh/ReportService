package test;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class LocalExceptionChangingListener implements IInvokedMethodListener, IReporter{
	
	  @Override
	  public void afterInvocation(IInvokedMethod method, ITestResult methodResult) {
	    if (methodResult.getStatus() == ITestResult.FAILURE) {
	      Throwable t = new Throwable("info " + methodResult.getThrowable().getMessage());
	      t.setStackTrace(methodResult.getThrowable().getStackTrace());
	      methodResult.setThrowable(t);
	    }
	  }

	  
	  
	 

	  private void generateReport(ISuite suite) {
	    List<ITestContext> testContexts =
	        suite.getResults().values().stream()
	            .map(ISuiteResult::getTestContext)
	            .collect(Collectors.toList());
	    List<ITestResult> failedResults =
	        testContexts.stream()
	            .flatMap(context -> context.getFailedConfigurations().getAllResults().stream())
	            .collect(Collectors.toList());
	    failedResults.addAll(testContexts.stream()
	        .flatMap(context -> context.getFailedTests().getAllResults().stream())
	        .collect(Collectors.toList())
	    );
	    failedResults.forEach(this::generateReport);
	  }

	  private void generateReport(ITestResult result) {
	    System.err.println("Method " + result.getMethod().getMethodName());
	    System.err.println("Type " + (result.getMethod().isTest() ? " test method " : "config method"));
	    System.err.println("Failure cause " + result.getThrowable().getMessage());
	    System.err.println("Exception object " + result.getThrowable().getClass().getName());
	  }

}
