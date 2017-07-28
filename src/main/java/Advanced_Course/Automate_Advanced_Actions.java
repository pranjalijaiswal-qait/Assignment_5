package Advanced_Course;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	ResultSet result;
	private By advance=By.linkText("Advanced Course");
	private By menu_2=By.cssSelector(".m2");
	private By go_next=By.xpath("//div[contains(@class,'menutop m2')] /span[contains(text(),'Go Next')]");
	private By symbol=By.id("symboldisplay");
	private By user_name=By.id("name");
	private By passkey=By.id("passkey");
	private By proceed= By.id("submit");
	String name="";
	String key="";
	
	public Automate_Advanced_Actions(WebDriver driver) {
		Automate_Advanced_Actions.driver=driver;
	}
	
	public void perform() throws InterruptedException, ClassNotFoundException, SQLException
	{
		driver.findElement(advance).click();
		hover_menu();
		get_Credentials();
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
	
	public int get_Id() throws ClassNotFoundException, SQLException
	{
		String text=driver.findElement(symbol).getText();
		connection("select id from identity where symbol='" + text+"'");
		result.next();
		return result.getInt(1);
	}
	
	public void get_Credentials() throws ClassNotFoundException, SQLException
	{
		connection("select name,passkey from credentials where id='"+get_Id()+"'");
		result.next();
		name=result.getString(1);
		key=result.getString(2);
	}
	public void enter_Credentials()
	{
		driver.findElement(user_name).sendKeys(""+name+"");
		driver.findElement(passkey).sendKeys(""+key+"");
		driver.findElement(proceed).click();
	}

}
