package Basic_Course;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Basic_Javascript {

	static WebDriver driver;
	String Box1_Colour="";
	String Box2_Colour="";
	By generate_Token=By.linkText("Generate Token");
	By token=By.id("token");
	JavascriptExecutor js;
	public Basic_Javascript(WebDriver driver)
	{
		Basic_Javascript.driver=driver;
	}
	public void getURL(String URL)
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/pranjalijaiswal/Downloads/chromedriver_win32/chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		js=(JavascriptExecutor)driver;
	}

	
	public void click_to_proceed() throws InterruptedException
	{
		 js.executeScript("document.getElementsByTagName('a')[0].click()");
		 js.executeScript("passthru();");
	     repainting();
		 drag_and_drop();
		 switch_Window();
		 cookie();
	}
	public String getColour_Box()
	{
		return (String) js.executeScript("return document.getElementById('answer').getAttribute('class')");
	}
	public void get_color()
	{
		driver.switchTo().frame("main");
		Box1_Colour = getColour_Box();
		driver.switchTo().frame("child");
		Box2_Colour=getColour_Box();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
	}
	public void repainting()
	{
		get_color();
		while(!(Box1_Colour.equalsIgnoreCase(Box2_Colour)))
		{
			js.executeScript("document.getElementsByTagName('a')[0].click()");
			driver.switchTo().defaultContent();
			get_color();
		}
		proceed();
	}
	public void proceed()
	{
		js.executeScript("gonext();");
	}
	public void drag_and_drop()
	{
		    WebElement fromElement=(WebElement)js.executeScript("return document.getElementById('dragbox')");
		    WebElement toElement=(WebElement)js.executeScript("return document.getElementById('dropbox')");
		    Actions action = new Actions(driver);
		    Action dragDrop = action.clickAndHold(fromElement).moveToElement(toElement).release(toElement).build();
		    dragDrop.perform(); 
			proceed();

	}
	public void switch_Window() throws InterruptedException
	{
		String parentHandle = driver.getWindowHandle();   
		js.executeScript("launchwindow();"); 
	    for (String winHandle : driver.getWindowHandles()) { 
	        driver.switchTo().window(winHandle);     
	    }
	    js.executeScript("document.getElementById('name').setAttribute('value','Pranjali Jaiswal')");
	    js.executeScript("document.getElementById('submit').click()");   
	    driver.switchTo().window(parentHandle);
		proceed();
	}
	public void cookie()
	{
		driver.navigate().to("http://10.0.1.86/tatoc/basic/cookie#");
		js.executeScript("generateToken();");
		String token_value=(String) js.executeScript("return document.getElementById('token').textContent");
		token_value=token_value.substring(7);
		Cookie name = new Cookie("Token",token_value);
		driver.manage().addCookie(name);
		proceed();
	}
	
}
