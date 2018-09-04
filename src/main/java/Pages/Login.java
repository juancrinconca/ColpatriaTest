package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Functions.GenFunctions;
import Parameters.Parameters;

/**Class login.
 * @author Juan Carlos Rincón
 */
public class Login extends GenFunctions{
	
	//Mapping Objects
	public static By lkLogIn = By.id("ccl_header_expand-login-link");
	public static By inpUser = By.id("username");
	public static By inpPass = By.id("password");
	public static By btLogin = By.id("login");
	public static By lbmsgerr = By.xpath("//div[@class='validation-summary-errors validation-message error']/span");
	public static WebElement image = null;
	
	/**Executes an enter to the login page.
	 * @throws Exception RuntimeException.
	 */
	public static void LoginPage() throws Exception {
		
		try {
			openBrowser(Parameters.url, Parameters.browser);
			
			if(waitForElement(driver,lkLogIn,Parameters.timeout)) {
				driver.findElement(lkLogIn).click();
				
				if(waitForElement(driver,inpUser,Parameters.timeout)) {
					driver.findElement(inpUser).sendKeys(Parameters.user);
					driver.findElement(inpPass).sendKeys(Parameters.Password);
					driver.findElement(btLogin).click();
					LogReport("Login",Parameters.msgFillForm,0,true);
					
					if(waitForElement(driver,lbmsgerr,Parameters.timeout)) {
						LogReport("Login",driver.findElement(lbmsgerr).getText().toString(),2,true);
					}
					else{
						LogReport("Login",Parameters.msgLogin,0,true);
					}
				}
				else{
					LogReport("Login",Parameters.ErrwaitElement+"LogIn Form.",2,true);
				}
			}
			else{
				LogReport("Login",Parameters.ErrwaitElement+"Link LogIn.",2,true);
			}
		} catch (Exception e) {
			
			LogReport("Runtime Error",e.toString(),3,true);
		}
	}
}
