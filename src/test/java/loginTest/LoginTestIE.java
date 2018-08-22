package loginTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static junit.framework.TestCase.fail;

public class LoginTestIE {
    @Test
    public void login_test() throws InterruptedException {
        //Declare the Gecko_Driver path
        System.setProperty("webdriver.ie.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/IEDriverServer.exe");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new InternetExplorerDriver();

        //Go to newtours website
        driver.get("http://newtours.demoaut.com/");

        driver.manage().window().maximize();

        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");
        driver.findElement(By.name("password")).sendKeys("testing");
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
        driver.quit();

    }


}
