package Basic_Course;


import java.util.concurrent.TimeUnit;
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
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
		driver.manage().window().maximize();
		driver.get(URL);
		js=(JavascriptExecutor)driver;
	}

	
	public void click_to_proceed()
	{
		 js.executeScript("document.getElementsByTagName('a')[0].click()");
		 js.executeScript("document.querySelector('.greenbox').click()");
	     repainting();
		 proceed();
		 drag_and_drop();
		 js.executeScript("document.getElementsByTagName('a')[1].click()");
		 switch_Window();
//		 proceed();
//		 cookie();
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
	}
	public void proceed()
	{
		js.executeScript("document.getElementsByTagName('a')[1].click()");
//		driver.switchTo().defaultContent();
	}
	public void drag_and_drop()
	{
		    WebElement fromElement=(WebElement)js.executeScript("return document.getElementById('dragbox')");
		    WebElement toElement=(WebElement)js.executeScript("return document.getElementById('dropbox')");
		    Actions action = new Actions(driver);
		    Action dragDrop = action.clickAndHold(fromElement).moveToElement(toElement).release(toElement).build();
		    dragDrop.perform(); 

	}
	public void switch_Window()
	{
		System.out.println("START>>>>>");
		js.executeScript("document.getElementsByTagName('a')[0].click()"); 
		System.out.println("clicked");
		String parentHandle = driver.getWindowHandle();   
		System.out.println(parentHandle);
	    for (String winHandle : driver.getWindowHandles()) { 
	        driver.switchTo().window(winHandle);     
	        System.out.println("switched");
	    }
	    js.executeScript("return document.getElementById('name').setAttribute('value','Pranjali Jaiswal')");
	    js.executeScript("document.getElementById('submit').click()");                          
	    driver.switchTo().window(parentHandle);
	}
//	public void cookie()
//	{
//		driver.navigate().to("http://10.0.1.86/tatoc/basic/cookie#");
//		click(generate_Token);
//		String token_value=driver.findElement(token).getText().substring(7);
//		Cookie name = new Cookie("Token",token_value);
//		driver.manage().addCookie(name);
//		click(proceed);
//	}
	
}
