package com.qa_infotech.demo.Assignment_5;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Automate_Basic_Actions {

	static WebDriver driver;
	String Box1_Colour="";
	String Box2_Colour="";
	By basicLink=By.linkText("Basic Course");
	By greenbox=By.className("greenbox");
	By mainFrame=By.id("main");
	By child_frame=By.id("child");
	By repaint=By.linkText("Repaint Box 2");
	By Box=By.id("answer");
	By proceed=By.linkText("Proceed");
	By drag=By.id("dragbox");
	By drop=By.id("dropbox");
	By launch_Pop_Up=By.linkText("Launch Popup Window");
	By name=By.id("name");
	By submit=By.id("submit");
	By generate_Token=By.linkText("Generate Token");
	By token=By.id("token");

	public Automate_Basic_Actions(WebDriver driver)
	{
		Automate_Basic_Actions.driver=driver;
	}
	public void getURL(String URL)
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/pranjalijaiswal/Downloads/chromedriver_win32/chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.manage().window().maximize();
		driver.get(URL);
	}
	public void switch_frames(By id)
	{
		driver.switchTo().frame(driver.findElement(id));
	}
	
	public void click(By Locator)
	{
		driver.findElement(Locator).click();
	}
	public void clickBasic()
	{
		click(basicLink);
	}
	public void greenBox()
	{
		click(greenbox);
	}
	public void repaint()
	{
	    click(repaint);
	}
	public String getColour_Box(By Box)
	{
		return driver.findElement(Box).getAttribute("class");
	}
	public void get_color()
	{
		switch_frames(mainFrame);
	    Box1_Colour=getColour_Box(Box);
		switch_frames(child_frame);
		Box2_Colour=getColour_Box(Box);
		driver.switchTo().defaultContent();
		switch_frames(mainFrame);
	}
	public void repainting()
	{
		get_color();
		while(!(Box1_Colour.equalsIgnoreCase(Box2_Colour)))
		{
			repaint();
			driver.switchTo().defaultContent();
			get_color();
		}
	}
	public void proceed()
	{
		click(proceed);
		driver.switchTo().defaultContent();
	}
	public void drag_and_drop()
	{
		    WebElement fromElement=driver.findElement(drag);
		    WebElement toElement=driver.findElement(drop);
		    Actions action = new Actions(driver);
		    Action dragDrop = action.dragAndDrop(fromElement, toElement).build();
		    dragDrop.perform(); 
	}
	public void switch_Window()
	{
		String parentHandle = driver.getWindowHandle(); 
	    click(launch_Pop_Up);                                //Clicking on this window
	    for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
	        driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)              
	    }
	    driver.findElement(name).sendKeys("Pranjali Jaiswal");
	    click(submit);                                
	    driver.switchTo().window(parentHandle);
	}
	public void cookie()
	{
		driver.navigate().to("http://10.0.1.86/tatoc/basic/cookie#");
		click(generate_Token);
		String token_value=driver.findElement(token).getText().substring(7);
		Cookie name = new Cookie("Token",token_value);
		driver.manage().addCookie(name);
		click(proceed);
	}
	
}
