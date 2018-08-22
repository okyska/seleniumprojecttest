package testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;


public class LoginTest_TestNG_Dependency {
    //Initialize web driver object
    WebDriver driver = null;

    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"})
    public void login_test() throws InterruptedException {
        //Declare the Gecko_Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        driver = new ChromeDriver();

        //Go to newtours website
        driver.get("http://newtours.herokuapp.com/");

        driver.manage().window().maximize();



        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");
        driver.findElement(By.name("password")).sendKeys("testing");
        driver.findElement(By.name("login")).click();

        //wait for 5 secs
        Thread.sleep(7000);


    }

        @Test(description = "Validate page tittle", groups = {"Integration Test"}, dependsOnMethods = { "login_test"})
        public void validateTitle(){
        //Print out the title
            System.out.println(driver.getTitle());

        //Validate the title
        if(driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test passed");
        }   else {
            System.out.println("Test case failed");
           fail("Test failed");
        }
        driver.quit();


    }



}
