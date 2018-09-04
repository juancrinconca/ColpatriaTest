package TestCases;

import org.testng.annotations.Test;
import Pages.Login;
import Functions.GenFunctions;

/**Class TestCase Login.
 * @author Juan Carlos Rincón
 */
public class TC1_Login extends GenFunctions{
    
	/**Execute the TC1 Login.
	 * @throws Exception RuntimeException.
	 */
	@Test (priority = 1)
	public static void TC1test() throws Exception{
		try {
			
			Login.LoginPage();
			driver.close();
		} catch (Exception e) {
			
			driver.close();
		}
		createReporter();
    }
}