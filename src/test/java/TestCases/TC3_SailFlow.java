package TestCases;

import org.testng.annotations.Test;
import Pages.SailFlow;
import Functions.GenFunctions;

/**Class TestCase Sail Flow.
 * @author Juan Carlos Rincón
 */
public class TC3_SailFlow extends GenFunctions{
    
	/**Execute the TC3 Sail Flow.
	 * @throws Exception RuntimeException.
	 */
	@Test (priority = 3)
	public static void TC3test() throws Exception{
		try {
			
			SailFlow.SailFlowPage();
			driver.close();
		} catch (Exception e) {
			
			driver.close();
		}
		createReporter();
    }
}