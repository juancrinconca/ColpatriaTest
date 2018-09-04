package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Functions.GenFunctions;
import Parameters.Parameters;

/**Class sail flow.
 * @author Juan Carlos Rincón
 */
public class SailFlow extends GenFunctions{
	
	//Mapping Objects
	public static By contQkSearch = By.className("cdc-filters-container");
	public static By btSailto = By.id("cdc-destinations");
	public static By lkSailto = By.xpath("//button[@class='cdc-filter-button'][contains(text(),'"+ Parameters.SailTo +"')]");
	public static By btSailFrom = By.id("cdc-ports");
	public static By lkSailfrom = By.xpath("//button[@class='cdc-filter-button'][contains(text(),'"+ Parameters.SailFrom +"')]");
	public static By btSailDate = By.id("cdc-dates");
	public static By lkSailDate = By.xpath("//button[@class='cdc-filter-button'][contains(@data-value,'"+ Parameters.SailDate +"')]");
	public static By btDuration = By.id("cdc-durations");
	public static By lkDuration = By.xpath("//button[@class='cdc-filter-button'][contains(text(),'"+ Parameters.SailDuration +"')]");
	public static By conSearchRes = By.xpath("//ccl-view-result-grid");
	public static By lbresults = By.xpath("//span[@class='sbsc-container__result-count']");
	public static By itCards = By.xpath("//article[@class='vrg-result-item vrg-result-item--result vrg-result-item--small']");
	
	/**Executes a cruise search in the quick search element and validate that the days for every itinerary be in the  range of the selected duration.
	 * @throws Exception RuntimeException.
	 */
	public static void SailFlowPage() throws Exception {
		
		try {
			
			openBrowser(Parameters.url, Parameters.browser);
			
			if(waitForElement(driver,contQkSearch,Parameters.timeout)) {
				
				driver.findElement(btSailto).click();
				Thread.sleep(2000);
				if(waitForElement(driver,lkSailto,Parameters.timeout) && driver.findElement(lkSailto).isEnabled()){
					driver.findElement(lkSailto).click();
					driver.findElement(btSailFrom).click();
					Thread.sleep(2000);
				
					if(waitForElement(driver,lkSailfrom,Parameters.timeout) && driver.findElement(lkSailfrom).isEnabled()){
						driver.findElement(lkSailfrom).click();
						driver.findElement(btSailDate).click();
						Thread.sleep(2000);
						
						if(waitForElement(driver,lkSailDate,Parameters.timeout) && driver.findElement(lkSailDate).isEnabled()){
							driver.findElement(lkSailDate).click();
							driver.findElement(btDuration).click();
							Thread.sleep(2000);
							
							if(waitForElement(driver,lkDuration,Parameters.timeout) && driver.findElement(lkDuration).isEnabled()){
								driver.findElement(lkDuration).click();
								Thread.sleep(2000);
								int min = Integer.parseInt(GenFunctions.getRangeDays(Parameters.SailDuration)[0]);
								int max = Integer.parseInt(GenFunctions.getRangeDays(Parameters.SailDuration)[1]);
								
								if(waitForElement(driver,conSearchRes,Parameters.timeout)) {
									LogReport("Cruise Search",Parameters.msgContRslt+driver.findElement(lbresults).getText(),0,true);
									List<WebElement> cards = driver.findElements(itCards);
									int i = 1;
									for(WebElement card : cards) {
										JavascriptExecutor js = (JavascriptExecutor) driver;
										js.executeScript("arguments[0].scrollIntoView();", card);
										String title = card.findElement(By.xpath(".//span[@class='cgc-cruise-glance__main-text']")).getText();
										String days = card.findElement(By.xpath(".//span[@class='cgc-cruise-glance__days']")).getText().trim();
										if(Integer.parseInt(days) >= min && Integer.parseInt(days) <= max){
											String description = Parameters.msgitineraryRange + "<br>" + title + "<br>" + "Days: " + days + " Range: " + min + "-" + max;
											LogReport("Itinerary "+i,description,0,false);
											i++;
										}
										else{
											String description = Parameters.ErritineraryRange + "<br>" + title + "<br>" + "Days: " + days + " Range: " + min + "-" + max;
											LogReport("Itinerary "+i,description,1,false);
											i++;
										}
									}
								}
								else {
									LogReport("Cruise Search",Parameters.ErrwaitElement+"Container Search Result.",2,true);
								}
							}
							else {
								LogReport("Sail Flow",Parameters.ErrEnbleElement+Parameters.SailDuration,2,true);
							}	
						}
						else {
							LogReport("Sail Flow",Parameters.ErrEnbleElement+Parameters.SailDate,2,true);
						}
					}
					else {
						LogReport("Sail Flow",Parameters.ErrEnbleElement+Parameters.SailFrom,2,true);
					}
				}
				else {
					LogReport("Sail Flow",Parameters.ErrEnbleElement+Parameters.SailTo,2,true);
				}				

			}
			else{
				LogReport("Sail Flow",Parameters.ErrwaitElement+"Quick Search.",2,true);
			}
		} catch (Exception e) {
			
			LogReport("Runtime Error",e.toString(),3,true);
		}
	}
}
