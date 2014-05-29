package TimeSheetAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	
	public static void main(String[] args) {

		if (args.length != 2){
			System.out.println("Please enter username, password and workorder(optional) as arguments");
			System.exit(1);
		}
    	System.setProperty("webdriver.chrome.driver", "./chromedriver");
    	
    	String workOrderString = "";
    	if (args[2]!=null) {
    		workOrderString = args[2];
    	}
    	User user = new User(args[0], args[1], workOrderString);
//    	User user = new User("yourUserName", "yourPassword", "yourWorkOrder");
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
        	driver.quit();    		

        	if (!timeSheet.checkIf40Hrs()){
        		System.out.println("Not filling up 40 hours");
        	} else if (isFillTimesheetSuccess && timeSheet.checkIf40Hrs()){
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
