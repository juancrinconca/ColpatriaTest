package Functions;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import Parameters.Parameters;

/**Class general fuctions.
 * @author Juan Carlos Rincón
 */
public class GenFunctions {

	/**Parameter with browser session.*/
	public static WebDriver driver = null;
	/**Parameter with test result status.*/
	public static ITestResult result;

	/**Start the selected browser.
	 * @param url page address.
	 * @param browser IE:InternetExplorer;CH:Chrome;FF:FireFox;ED:Edge.
	 * @throws Exception RuntimeException.
	 */
	@SuppressWarnings("deprecation")
	public static void openBrowser(String url, String browser) throws Exception {
		switch(browser) {
		case "IE":
			File ieFile = new File(Parameters.PathDriver + "/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());
			DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
			ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, url);
			ieCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(ieCaps);
			break;
		case "CH":
			System.setProperty("webdriver.chrome.driver", Parameters.PathDriver + "/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "FF":
			System.setProperty("webdriver.gecko.driver", Parameters.PathDriver + "/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "ED":
			System.setProperty("webdriver.edge.driver", Parameters.PathDriver + "/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		LogReport("Open Browser",Parameters.msgOpenbr,0,true);
	}

	/**Wait for an existed By element in the page
	 * @param driver Connection with the driver that have the open session.
	 * @param webElementId By element on the page.
	 * @param timeOut wait time in seconds for a element.
	 * @return boolean true:element exists; false:element not exists.
	 * @throws Exception RuntimeException.
	 */
	public static boolean waitForElement(WebDriver driver, final By webElementId, int timeOut) throws Exception{

		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(webElementId));
			return Boolean.TRUE;
		} catch (Exception e) {

			return Boolean.FALSE;
		}
	}

	/**Generate a status for events in the page.
	 * @param module Action's Name.
	 * @param message Action's Description.
	 * @param level Action´s status, 0:INFO; 1:WARNING; 2:ERROR; 3:FATAL.
	 * @param flag Establish if take a screenshot.
	 * @throws Exception RuntimeException.
	 */
	public static void LogReport(String module, String message, int level, boolean flag) throws Exception {

		result = Reporter.getCurrentTestResult();
		switch (level){
		default: //Information
			fillReporter(module,"INFO",message,takeScreenshot(flag));
			break;
		case 1: //Warning
			result.setStatus(ITestResult.SKIP);
			Reporter.setCurrentTestResult(result);
			fillReporter(module,"WARNING",message,takeScreenshot(flag));
			break;
		case 2: //Error
			result.setStatus(ITestResult.FAILURE);
			Reporter.setCurrentTestResult(result);
			fillReporter(module,"ERROR",message,takeScreenshot(flag));
			throw new RuntimeException(module + ": " + message);
		case 3: //Fatal
			result.setStatus(ITestResult.FAILURE);
			Reporter.setCurrentTestResult(result);
			fillReporter(module,"FATAL",message,takeScreenshot(flag));
			throw new RuntimeException(module + ": " + message);
		}
	}

	/**Generate the HTML report.
	 */
	public static void createReporter(){
		String html = "<table border=\"1\"><tr><th>ACTION</th><th>STATUC</th><th>DESCRIPTION</th><th>IMAGE</th></tr>"
				+Parameters.htmlReport+"</table>"; 
		Reporter.log("<table border=\"1\"><tr><th>Inicio Prueba</th><th>"+Parameters.startTime+"</th></tr></table>");
		Reporter.log(html);
		Parameters.endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		Parameters.startTime = Parameters.endTime;
		Reporter.log("<table border=\"1\"><tr><th>Fin Prueba</th><th>"+Parameters.endTime+"</th></tr></table>");
		Parameters.htmlReport = "";
	}

	/**Build a HTML structure for the report.
	 * @param module Action's Name.
	 * @param status Action's status. INFO; WARN; ERROR; FATAL.
	 * @param comments Action´s description
	 * @param srcImgFile Path where the image exists.
	 */
	public static void fillReporter(String module, String status, String comments, String srcImgFile){
		Parameters.htmlReport = Parameters.htmlReport+
				"<tr><th>"+module+"</th><th>"+status+"</th><th align=\"left\">"+comments+"</th><th>"+srcImgFile+"</th>";
	}

	/**Gets a screenshot for the current screen.
	 * @param flag Establish if take a screenshot.
	 * @throws Exception RuntimeException.
	 * @return Path where the image exists.
	 */
	public static String takeScreenshot(boolean flag) throws Exception {

		Robot rbt = new Robot();
		Point position = null;
		Dimension dimension = null;
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File ScrFile = new File(".\\test-output\\Screenshots\\"+timeStamp+".png");
		String path = "<img class=\"img\" width=\"40%\"  id=\""+timeStamp+"\" src="+".\\Screenshots\\"+timeStamp+".png"+" alt=\"BVE\"/>"; 
		Rectangle capture = null;

		if(flag==true){

			position = driver.manage().window().getPosition();
			dimension = driver.manage().window().getSize();
			capture = new Rectangle(position.getX(),position.getY(),dimension.width,dimension.height);
			BufferedImage Image = rbt.createScreenCapture(capture);
			ImageIO.write(Image, "png", ScrFile);
		}
		else {
			path = "";
		}
		return path;
	}

	/**Gets the range of days depends a option.
	 * @param option Value with the range in a string.
	 * @return String[] Array with range limits (min-max).
	 */
	public static String[] getRangeDays(String option) {
		
		String range[] = new String[2];
		switch(option) {
			case "2 - 5 Days":
				range[0]="2";
				range[1]="5";
				break;
			case "6 - 9 Days":
				range[0]="6";
				range[1]="9";			
				break;
			case "10+ Days":
				range[0]="10";
				range[1]="99";
				break;			
		}
		return range;
	}
	
	/**Set parameters before start execution*/
	@BeforeSuite(alwaysRun = true)
	public static void beforeRun() {
		Parameters.startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		File folders = new File(".\\test-output\\Screenshots\\");
		folders.mkdirs();
	}
}