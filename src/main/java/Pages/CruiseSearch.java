package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Functions.GenFunctions;
import Parameters.Parameters;

/**Class cruise search.
 * @author Juan Carlos Rincón
 */
public class CruiseSearch extends GenFunctions{

	//Mapping Objects
	public static By lkPlan = By.xpath("//a[@href='/cruise-search'][@aria-label='Plan']");
	public static By conSearchRes = By.xpath("//ccl-view-result-grid[@class='ng-scope ng-isolate-scope']");
	public static By lbresults = By.xpath("//span[@class='sbsc-container__result-count ng-binding ng-scope']");
	public static By itCards = By.xpath("//article[@class='vrg-result-item ng-scope vrg-result-item--result vrg-result-item--small']");
	public static By contItinerary = By.id("itineraryHero");
	public static By lbtitle = By.xpath("//span[@id='cruiseDescrGlance']");
	public static By lbdays = By.xpath("(//div[@class='duration-title']/p/span)[2]");
	public static By lbship = By.xpath("(//span[@class='ship-name ng-binding'])[2]");
	public static By lbprice = By.xpath("(//span[@class='price-wrapper'])[1]");
	
	/**Selects find a cruise option in the menu and validate the information of some cards that contains a information for the trip and select the first itinerary to compare those information.
	 * @throws Exception RuntimeException.
	 */
	public static void CruiseSearchPage() throws Exception {
	
		try {
			
			boolean flag = true;
			
			openBrowser(Parameters.url, Parameters.browser);
			
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(lkPlan)).perform();
			Thread.sleep(2000);
			driver.findElement(lkPlan).click();
			
			if(waitForElement(driver,conSearchRes,Parameters.timeout)) {
				LogReport("Cruise Search",Parameters.msgContRslt+driver.findElement(lbresults).getText(),0,true);
				
				List<WebElement> cards = driver.findElements(itCards);
				String values[][] = new String[cards.size()][4];
				int i = 0;
				for(WebElement card : cards) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", card);
					values[i][0] = card.findElement(By.xpath(".//span[@class='cgc-cruise-glance__main-text ng-binding']")).getText();
					values[i][1] = card.findElement(By.xpath(".//span[@class='cgc-cruise-glance__secondary-text ng-binding']")).getText();
					values[i][2] = card.findElement(By.xpath(".//span[@class='cgc-cruise-glance__days ng-binding']")).getText();
					values[i][3] = card.findElement(By.xpath(".//span[@class='vrgf-price-box__price ng-binding']")).getText();
					
					String description = "Itinerary " + (i+1) + "<br>" + values[i][0] + "<br>" + "Ship: " + values[i][1] + "<br>" + "Days: " + values[i][2] + "<br>" + "Price: " + values[i][3];
					LogReport("Cruise Search",description,0,false);
					i++;
				}
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", cards.get(0));
				cards.get(0).findElement(By.xpath(".//a[@class='vrgf-learn-more__text ng-binding']")).click();
				
				if(waitForElement(driver,contItinerary,Parameters.timeout)) {
					for(int j=0;j<values[0].length;j++) {
						switch (j){
							case 0:
								if(!driver.findElement(lbtitle).getText().trim().equals(values[0][0].trim())){
									flag=false;}
								break;
							case 1:
								if(!driver.findElement(lbship).getText().trim().equals(values[0][1].trim())){
									flag=false;}
								break;
							case 2:
								if(!driver.findElement(lbdays).getText().trim().equals(values[0][2].trim())){
									flag=false;}
								break;
							case 3:
								if(!driver.findElement(lbprice).getText().trim().equals(values[0][3].trim().replace("*"," *"))){
									flag=false;}
								break;
						}					
					}
					if(flag){
						LogReport("Itinerary 1",Parameters.msgitinerary,0,true);
					}
					else{
						LogReport("Itinerary 1",Parameters.Erritinerary,2,true);
					}
				}
				else{
					LogReport("Cruise Search",Parameters.ErrwaitElement+"Container Itinerary.",2,true);
				}
			}
			else {
				LogReport("Cruise Search",Parameters.ErrwaitElement+"Container Search Result.",2,true);
			}
		} catch (Exception e) {
			
			LogReport("Runtime Error",e.toString(),3,true);
		}
	}
}
