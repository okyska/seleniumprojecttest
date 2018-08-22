package testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.fail;


public class LoginTest_TestNG_DataProvider {
    //Initialize web driver object
    WebDriver driver = null;

    @BeforeMethod(description = "Runs before your test", groups = { "Smoke Test", "Integration Test"})
    public void beforeMethod(){
        //Declare the Gecko_Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        driver = new ChromeDriver();

        //Go to newtours website
        driver.get("http://newtours.herokuapp.com/");

        driver.manage().window().maximize();
    }
    @DataProvider(name = "User Credentials")
    public static Object[][] getCredentials(){
        return new Object[][]{
                {"seleniumguru", "test1234"},
                {"testing", "testing"},
                {"selenium", "selenium"}
        };
    }

    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"}, dataProvider = "User Credentials")

    public void login_test(String username, String password) throws InterruptedException {


        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        //wait for 5 secs
        Thread.sleep(7000);
        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if(driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test passed");
        }   else {
            fail("Test failed");
        }



    }



    @AfterMethod(description = "Close browser", groups = {"Smoke Test", "Integration Test"})
    public void tearDown(){
        //Destroy the driver instance and close the browser
        driver.quit();

    }

}
