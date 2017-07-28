package Basic_Course;

import org.openqa.selenium.WebDriver;

public class Automate_Basic {
	
    static WebDriver driver;
    static String URL="http://10.0.1.86/tatoc";
    static Automate_Basic_Actions action=new Automate_Basic_Actions(driver);
    static Basic_Javascript javascript=new Basic_Javascript(driver);
    
	public static void main(String args[])
	{
	 javascript.getURL(URL);
	 javascript.click_to_proceed();
	}
}
