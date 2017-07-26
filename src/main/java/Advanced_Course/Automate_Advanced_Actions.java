package Advanced_Course;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Automate_Advanced_Actions {
	
	static WebDriver driver;
	String dbUrl="jdbc:mysql://10.0.1.86/tatoc";
	String username="tatocuser";
	String password="tatoc01";
	String name="";
	String credential="";
	String identity="";
	ResultSet result;
	String credential_name[]=new String[26];
	String credential_pwd[]=new String[26];
	String symbols[]=new String[30];
	private By advance=By.linkText("Advanced Course");
	private By menu_2=By.cssSelector(".m2");
	private By go_next=By.xpath("//span[contains(@class,'menuitem')]  [contains(text(),'Go Next')]");
	private By symbol=By.id("symboldisplay");
	private By user_name=By.id("name");
	private By passkey=By.id("passkey");
	private By proceed= By.id("submit");
	
	public Automate_Advanced_Actions(WebDriver driver) {
		Automate_Advanced_Actions.driver=driver;
	}
	
	public void perform() throws InterruptedException, ClassNotFoundException, SQLException
	{
		driver.findElement(advance).click();
		hover_menu();
		get_values_from_database();
		enter_Credentials();
	}
	public void getURL(String URL)
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/pranjalijaiswal/Downloads/chromedriver_win32/chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.manage().window().maximize();
		driver.get(URL);
	}
	public void hover_menu() throws InterruptedException
	{
	    Actions action = new Actions(driver);
	    WebElement element = driver.findElement(menu_2);
	    action.moveToElement(element).moveToElement(driver.findElement(go_next)).click().build().perform();
	}
	public void connection(String Query) throws SQLException, ClassNotFoundException
	{
		Connection con = DriverManager.getConnection(dbUrl,username,password); 
		Class.forName("com.mysql.jdbc.Driver");
		Statement statement = con.createStatement();	
		result= statement.executeQuery(Query);	
	}
	public void Credential_database() throws SQLException
	{
		int index=0;
		while (result.next()){
	    	 name = result.getString(2);
	    	 credential = result.getString(3);
	    	 credential_name[index]=name;
	    	 credential_pwd[index]=credential;
	    	 index++;
			}
	}
	public void Identity_database() throws SQLException
	{
		int index=0;
		while (result.next()){
	    	 identity = result.getString(2);
	         symbols[index]=identity;
	         index++;
			}		
	}
	public void get_values_from_database() throws ClassNotFoundException, SQLException
	{
		connection("Select * from credentials");
		Credential_database();
		connection("Select * from identity");
		Identity_database();
	}
	public int get_Credentials()
	{
		int tag=0;
		String text=driver.findElement(symbol).getText();
		for(int index=0;index<symbols.length;index++)
		{
			if(text.equalsIgnoreCase(symbols[index]))
			{
				tag=index;
				break;
			}
		}
		return tag;
	}
	public void enter_Credentials()
	{
		int value=get_Credentials();
		driver.findElement(user_name).sendKeys(""+credential_name[value]+"");
		driver.findElement(passkey).sendKeys(""+credential_pwd[value]+"");
		driver.findElement(proceed).click();
	}

}
