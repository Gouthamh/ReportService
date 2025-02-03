package reportservice;


import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tecnotree.automatiom.Methods.Method;
import com.tecnotree.automatiom.SS0_aaccess_token.Readfromapplicationprpoerities;
import com.tecnotree.automatiom.jsondataprovide.requestpayload;
import com.tecnotree.automatiom.routers.Routers;
import com.tecnotree.automatiom.routers.ValidatorOperation;
import com.tecnotree.automatiom.utilities.ExtractFileName;
import com.tecnotree.automatiom.utilities.FileDownloaders;
import com.tecnotree.automatiom.utilities.FileService;
import com.tecnotree.automatiom.utilities.Fileupload;

public class test1 {

	private static FileService fileService;
	private static ExtractFileName urlService;
	private static FileDownloaders fileDownloader;
	private static Method method;
	private static long initialcount;

	@Test(dataProvider = "s",retryAnalyzer = com.tecnotree.automatiom.listerns.RetryAnalyzer.class)
	public static void test001(String a, String b) {

		fileService = new FileService();
		urlService = new ExtractFileName();
		fileDownloader = new FileDownloaders(fileService, urlService);
		initialcount = fileDownloader.countFiles();

		method = new Method();
		String FileUrl = method
				.Post(a, Readfromapplicationprpoerities.application("application.properties", "BaseUrl"),
						Routers.dap_reports, Routers.template_to_report)
				.assertIt(200).assertIt("type", "Success", ValidatorOperation.EQUALS)
				.assertIt("reportName", b, ValidatorOperation.HAS_STRING).extractString("ATTACHMENT_URL[0]");

		int finalcount = fileDownloader.downloadFile(FileUrl.toString()).countFiles();
		Assert.assertEquals(initialcount + 1, finalcount);
		Assert.assertEquals(true, Fileupload.getfileexe("Documents", method.extractString("reportName")));

	}

	@Test(dataProvider = "ziping",retryAnalyzer = com.tecnotree.automatiom.listerns.RetryAnalyzer.class)
	public static void test002(String a, String b) {

		fileService = new FileService();
		urlService = new ExtractFileName();
		fileDownloader = new FileDownloaders(fileService, urlService);
		initialcount = fileDownloader.countFiles();

		method = new Method();
		String FileUrl = method
				.Post(a, Readfromapplicationprpoerities.application("Application.properties", "BaseUrl"),
						Routers.dap_reports, Routers.template_to_report)
				.assertIt(200).assertIt("type", "Success", ValidatorOperation.EQUALS)
				.assertIt("reportName", b, ValidatorOperation.HAS_STRING).extractString("ATTACHMENT_URL[0]");

		int finalcount = fileDownloader.downloadFile(FileUrl.toString()).countFiles();
		Assert.assertEquals(initialcount + 1, finalcount);
		Assert.assertEquals(true, Fileupload.getfileexe("Documents", method.extractString("reportName")));

	}

	@Test(dataProvider = "password",retryAnalyzer = com.tecnotree.automatiom.listerns.RetryAnalyzer.class)
	public static void test003(String a, String b) {

		fileService = new FileService();
		urlService = new ExtractFileName();
		fileDownloader = new FileDownloaders(fileService, urlService);
		initialcount = fileDownloader.countFiles();

		method = new Method();
		String FileUrl = method
				.Post(a, Readfromapplicationprpoerities.application("Application.properties", "BaseUrl"),
						Routers.dap_reports, Routers.template_to_report)
				.assertIt(200).assertIt("type", "Success", ValidatorOperation.EQUALS)
				.assertIt("reportName", b, ValidatorOperation.HAS_STRING).extractString("ATTACHMENT_URL[0]");

		int finalcount = fileDownloader.downloadFile(FileUrl.toString()).countFiles();
		Assert.assertEquals(initialcount + 1, finalcount);
		Assert.assertEquals(true, Fileupload.getfileexe("Documents", method.extractString("reportName")));

	}

	@DataProvider(name = "s")
	public Object[] dataproviders() throws FileNotFoundException {
		return requestpayload.getData1("BulkConfigu.json", "dataSet", "templateName");
	}

	@DataProvider(name = "ziping")
	public Object[] dataproviders1() throws FileNotFoundException {
		return requestpayload.getData1("ZipingFile.json", "dataSet", "templateName");
	}

	@DataProvider(name = "password")
	public Object[] dataproviders2() throws FileNotFoundException {
		return requestpayload.getData1("PasswordReport.json", "dataSet", "templateName");
	}

}
