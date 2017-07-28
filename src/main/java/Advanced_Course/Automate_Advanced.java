package Advanced_Course;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;

public class Automate_Advanced {
	
	static WebDriver driver;
    static String URL="http://10.0.1.86/tatoc";
    static Automate_Advanced_Actions actions=new Automate_Advanced_Actions (driver);
    static AdvancedRestAPI rest=new AdvancedRestAPI(driver);

	public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
		actions.getURL(URL);
		actions.perform();
		rest.init();
		rest.RegisterforAccess();
		rest.fileHandle();

	}

}
