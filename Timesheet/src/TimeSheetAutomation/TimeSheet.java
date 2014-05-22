package TimeSheetAutomation;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TimeSheet  {

	private WebDriver driver;
	private User user;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	
	public TimeSheet(WebDriver driver, User user){
		this.driver = driver;
		this.user = user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public void login(){
        driver.get("https://prod2.beeline.com/allegisgm?7c50eeec67f8435289f4d67b6423e053");

        WebElement elemUsername = driver.findElement(By.id("beelineForm_userNameText"));
        elemUsername.sendKeys(user.getUsername());

        WebElement elemPassword = driver.findElement(By.id("beelineForm_passwordText"));
        elemPassword.sendKeys(user.getPassword());
        
        WebElement elemLogin = driver.findElement(By.id("defaultActionLinks_loginAction"));
        elemLogin.click();	
	}
	
	public boolean waitForTimeSheet(int sec) throws InterruptedException{
		System.out.println("waiting for timesheet to show... "+sec + " sec");
		if (sec >120){
			System.out.println("I've been waited for 2 minutes! Maybe thewebsite is not responding.  Maybe your username/pwd is not correct.  Check your login.");
			return false;
		}
		try {
			driver.findElement(By.id("Assignment_0_AssignmentDetail_0_TimesheetRowGroup_0_Task_0_TaskComboSelector_Input"));
			return true;
		} catch(NoSuchElementException e){
			Thread.sleep(1000);
			return waitForTimeSheet(sec+1);
		}		
	}
	
	public boolean fillTimeSheet(){
		
        WebElement elemWorkOrder = driver.findElement(By.id("Assignment_0_AssignmentDetail_0_TimesheetRowGroup_0_Task_0_TaskComboSelector_Input"));
        elemWorkOrder.sendKeys(user.getWorkOrder());
		
        WebElement elemPayCode = driver.findElement(By.id("Assignment_0_AssignmentDetail_0_TimesheetRowGroup_0_Task_0_PayCodeComboSelector_Input"));
        elemPayCode.sendKeys("Straight Time");
        
        WebElement elemShift = driver.findElement(By.id("Assignment_0_AssignmentDetail_0_TimesheetRowGroup_0_Task_0_ShiftComboSelector_Input"));
       elemShift.sendKeys("1st");
       
       for (int i = 0; i < 5; i++){
    	   WebElement elemDay = driver.findElement(By.id("Assignment_0_AssignmentDetail_0_TimesheetRowGroup_0_Task_0_EntryTextBox_" + i));
    	   if (!fillTimeForTheDay(elemDay, i)){
    		   return false;
    	   }
       }
       return true;
	}
	
	public void submitTimesheet(){
		WebElement elemSubmitButton = driver.findElement(By.id("submitTimesheetButton"));
		elemSubmitButton.click();
	}
	
	public boolean fillTimeForTheDay(WebElement elemDay, int day){
		try{       
			elemDay.sendKeys("8");
			return true;
		} catch (InvalidElementStateException e){    	   
			System.out.println(days[day] +" is not available yet.  Too bad we can't fill time in advance...");
			return false;
		}
	}
	

}