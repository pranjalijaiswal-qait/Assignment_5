package com.qa_infotech.demo.Assignment_5;

import org.openqa.selenium.WebDriver;

public class Automate_Basic {
	
    static WebDriver driver;
    static String URL="http://10.0.1.86/tatoc";
    static Automate_Basic_Actions action=new Automate_Basic_Actions(driver);
    
	public static void main(String args[])
	{
	 action.getURL(URL);
	 action.clickBasic();
	 action.greenBox();
	 action.repainting();
	 action.proceed();
	 action.drag_and_drop();
	 action.proceed();
	 action.switch_Window();
	 action.proceed();
	 action.cookie();
	}
}
