package advancedSelenium;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Alert_Accept {
    @Test
    public void alert_test() throws InterruptedException {
        //Declare the Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new ChromeDriver();

        //Go to orbitz website
        driver.get("http://ksrtc.in/");

        driver.manage().window().maximize();

        WebElement searchButton = driver.findElement(By.id("searchBtn"));
        searchButton.click();

        //Create an alert object for the pop up
        Alert alert = driver.switchTo().alert();

        //Get text from alert box
        System.out.println(alert.getText());

        //accept the alert
        alert.accept();

        driver.quit();
    }
}
