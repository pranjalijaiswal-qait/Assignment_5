package Advanced_Course;

import static com.jayway.restassured.RestAssured.given;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
public class AdvancedRestAPI 
{
		Response response;
		WebDriver driver;
		By sessionId = By.id("session_id");
		By proceed = By.linkText("Proceed");
		By downloadFile = By.linkText("Download File");
		By signatureValue = By.cssSelector("input#signature");
		By Submit = By.className("submit");
		By endOfBasicCourse = By.cssSelector("Span[class='finish']");
		String Signature = "";
		String sessionID="";
		public AdvancedRestAPI(WebDriver driver) {
			this.driver = driver;
		}
		public void init()
		{
			System.setProperty("webdriver.chrome.driver","C:/Users/pranjalijaiswal/Downloads/chromedriver_win32/chromedriver.exe");
			driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.manage().window().maximize();
		}

		public String getGeneratedToken() {

			sessionID = driver.findElement(sessionId).getText().substring(12);
			String token;
			response = given().get("http://10.0.1.86/tatoc/advanced/rest/service/token/" + sessionID).then()
					.contentType(ContentType.JSON).extract().response();
			token = response.jsonPath().getString("token");
			return token;
		}

		public void clickOnProceed() {
			driver.findElement(proceed).click();
		}

		public void RegisterforAccess() {
			driver.navigate().to("http://10.0.1.86/tatoc/advanced/rest/#");
			response = given().contentType(ContentType.JSON).when()
					.post("http://10.0.1.86/tatoc/advanced/rest/service/register?signature=" + getGeneratedToken() + "&id="
							+ sessionID + "&allow_access=1");
			clickOnProceed();
		}
		public void fileHandle()
		{
			clickOnDownload();
			File();
			driver.findElement(signatureValue).sendKeys(Signature);
			driver.findElement(Submit).click();
		}

		public void clickOnDownload() {
			driver.findElement(downloadFile).click();
		}

		public void File() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			File file = new File("C:/Users/pranjalijaiswal/Downloads/file_handle_test.dat");
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				int count = 0;
				while ((line = br.readLine()) != null) {
					count++;
					if (count == 3) {
						Signature = line.substring(11);
					}
				}
	      		br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


	}

