package testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;


public class LoginTest_TestNG_Parameterized {
    //Initialize web driver object
    WebDriver driver = null;

    @BeforeSuite(description = "Runs before your test", groups = { "Smoke Test", "Integration Test"})
    public void beforeSuite(){
        //Declare the Gecko_Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        driver = new ChromeDriver();

        //Go to newtours website
        driver.get("http://newtours.herokuapp.com/");

        driver.manage().window().maximize();
    }

    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"})
    @Parameters({"username", "password"})
    public void login_test(String username, String password) throws InterruptedException {


        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        //wait for 5 secs
        Thread.sleep(7000);


    }

        @Test(description = "Validate page tittle", groups = {"Integration Test"})
        public void validateTitle(){
            //Print out the title
            System.out.println(driver.getTitle());

        //Validate the title
        if(driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test passed");
        }   else {
           fail("Test failed");
        }


    }

    @AfterSuite(description = "Close browser", groups = {"Smoke Test", "Integration Test"})
    public void tearDown(){
        //Destroy the driver instance and close the browser
        driver.quit();

    }

}
