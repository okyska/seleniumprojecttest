package dockerswarm;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.fail;

public class Grid_test_firefox {
    //Initialize web driver object
    WebDriver driver = null;

    @BeforeSuite(description = "Runs before your test", groups = { "Smoke Test", "Integration Test"})
    public void beforeSuite() throws MalformedURLException {

        //Configured the hub url
        String hubURL = "http://192.168.99.100:4444/wd/hub";

        //Create a Desired Capabilities object
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("firefox");
        capabilities.acceptInsecureCerts();

        //Create a web driver object
        driver = new RemoteWebDriver(new URL(hubURL), capabilities);

        //Go to newtours website
        driver.get("http://newtours.herokuapp.com/");

        driver.manage().window().maximize();
    }

    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"})
    public void login_test() throws InterruptedException {


        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");
        driver.findElement(By.name("password")).sendKeys("testing");
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
