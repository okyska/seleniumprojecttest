package advancedSelenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IFrameExample {

    @Test
    public void iFrame_test() {
        //Declare the Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new ChromeDriver();

        //Go to iFrame website
        driver.get("file:///Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/iframe.html");

        driver.manage().window().maximize();

        //Switch to default content
        driver.switchTo().defaultContent();

        //Count number of iFrames
        int numberOfFrames = driver.findElements(By.tagName("iframe")).size();
        System.out.println(numberOfFrames);

        //Switch to 1st iFrame by index
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();

        driver.switchTo().defaultContent();

        //Switch to 2nd iFrame by id
        driver.switchTo().frame("iFrame2");
        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();


    }
}
