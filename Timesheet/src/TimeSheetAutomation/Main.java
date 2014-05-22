package TimeSheetAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	
	public static void main(String[] args) {

    	System.setProperty("webdriver.chrome.driver", "./chromedriver");

    	User user = new User("yourUserName", "yourPassword", "yourWorkOrder");
        WebDriver driver = new ChromeDriver();
        TimeSheet timeSheet = new TimeSheet(driver, user);
        
        timeSheet.login();
        
        boolean isWaitForTimeSheetDone = false;
        try {
        	isWaitForTimeSheetDone = timeSheet.waitForTimeSheet(0);
        } catch (InterruptedException e){
        	e.printStackTrace();
        }

        if (isWaitForTimeSheetDone){
        	boolean isFillTimesheetSuccess = timeSheet.fillTimeSheet(); 
        
        	//disable this line to see the browser completed
        	//driver.quit();    		

        	if (isFillTimesheetSuccess){
        		timeSheet.submitTimesheet();
        		System.out.println("TimeSheet Completed");
        		System.exit(0);
        	} else {
        		System.out.println("TimeSheet Not Completed");
        	}
        }
		System.exit(1);


    }
}
