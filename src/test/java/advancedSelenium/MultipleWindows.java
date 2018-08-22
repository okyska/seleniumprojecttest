package advancedSelenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleWindows {
    @Test
    public void alert_test() throws InterruptedException {
        //Declare the Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webDriver class and create object
        WebDriver driver = new ChromeDriver();

        //Go to website
        driver.get("http://the-internet.herokuapp.com/windows");

        driver.manage().window().maximize();

        //Get the current window handle
        String parentWindow = driver.getWindowHandle();

        //Click button go to a new window
        driver.findElement(By.linkText("Click Here")).click();

        Thread.sleep(2000);

        //for loop all the window handles
        for(String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
            System.out.println(driver.getTitle());
        }

        //Switch back to parent window
        driver.switchTo().window(parentWindow);

        driver.quit();

    }
}
