package calendar;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.fail;

public class calendar {
    @Test
    public void calendar_test() throws InterruptedException {
        //Declare the Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new ChromeDriver();

        //Go to orbitz website
        driver.get("http://orbitz.com/");

        driver.manage().window().maximize();

        //Click on flights button
        WebElement flightsButton = driver.findElement(By.xpath("//button[@id='tab-flight-tab-hp']"));
        flightsButton.click();

        //Click on departing textbox
        WebElement departTextbox = driver.findElement(By.xpath("//input[@id='flight-departing-hp-flight']"));
        departTextbox.click();

        //Explicit wait untul calendar object is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='datepicker-cal-weeks']")));

        //Using the loop for the calendar object
        WebElement calendarObject = driver.findElement(By.xpath("//table[@class='datepicker-cal-weeks']"));
        List<WebElement> departList = calendarObject.findElements(By.tagName("td"));
        String departDate = "August\n" + "5";

        for (WebElement cell: departList){
            if (cell.getText().equals(departDate)){
                System.out.println("Sell found");
                cell.click();
                break;
            } else{
                System.out.println(cell.getText());
                System.out.println("Sell not found");
                //fail("departDate fail");
            }
        }

        //Not using the loop for return date
        WebElement returnTextbox = driver.findElement(By.xpath("//input[@id='flight-returning-hp-flight']"));
        returnTextbox.click();

        //Explicit wait until calendar object is present
        WebDriverWait waitforReurn = new WebDriverWait(driver, 10);
        waitforReurn.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='datepicker-cal-weeks']")));

        WebElement returnDay = driver.findElement(By.xpath("//table[@class='datepicker-cal-weeks']//button[@data-year='2018'][@data-month='8'][@data-day='30']"));
        returnDay.click();



        driver.quit();
    }
}
