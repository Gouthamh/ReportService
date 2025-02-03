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

public class FileName {

	private static FileService fileService;
	private static ExtractFileName urlService;
	private static FileDownloaders fileDownloader;
	private static Method method;
	private static long initialcount;

	@Test(dataProvider = "ReportsFilesName",retryAnalyzer = com.tecnotree.automatiom.listerns.RetryAnalyzer.class)
	public static void test004(String a, String b) {

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
	public static void test005(String a, String b) {

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


	@DataProvider(name = "ReportsFilesName")
	public Object[] dataproviders1() throws FileNotFoundException {
		return requestpayload.FileNameCombine("ReportsFilesName.json", "dataSet","templateName","nameOfAfile");
	}
	@DataProvider(name = "ReportsFilesNameandZip")
	public Object[] dataproviders2() throws FileNotFoundException {
		return requestpayload.FileNameCombine("ReportsFilesNameandZip.json", "dataSet","templateName","nameOfAfile");
	}

}
