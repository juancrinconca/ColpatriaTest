package TestCases;

import org.testng.annotations.Test;

import Pages.CruiseSearch;
import Functions.GenFunctions;

/**Class TestCase Cruise Search.
 * @author Juan Carlos Rincón
 */
public class TC2_CruiseSearch extends GenFunctions{
    
	/**Execute the TC2 Cruise Search.
	 * @throws Exception RuntimeException.
	 */
	@Test (priority = 2)
	public static void TC2test() throws Exception{
		try {
			
			CruiseSearch.CruiseSearchPage();
			driver.close();
		} catch (Exception e) {
			
			driver.close();
		}
		createReporter();
    }
}