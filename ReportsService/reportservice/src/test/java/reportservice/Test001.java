package reportservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test001 {

	@Test(retryAnalyzer = com.tecnotree.automatiom.listerns.RetryAnalyzer.class)
	//@Test
	public void Test1()
	{
		Assert.assertEquals(false, true);
	}

	@Test
	public void Test2()
	{
		Assert.assertEquals(false, true);
	}
}
