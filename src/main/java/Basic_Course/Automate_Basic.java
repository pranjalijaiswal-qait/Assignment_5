package Basic_Course;

import org.openqa.selenium.WebDriver;
public class Automate_Basic {
	
    static WebDriver driver;
    static String URL="http://10.0.1.86/tatoc";
    static Automate_Basic_Actions action=new Automate_Basic_Actions(driver);
    static Basic_Javascript script=new Basic_Javascript(driver);
    
	public static void main(String args[]) throws InterruptedException
	{
		action.getURL(URL);
		action.click_to_proceed();
	    script.getURL(URL);
	    script.click_to_proceed();
	}
}
