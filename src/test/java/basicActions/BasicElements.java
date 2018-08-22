package basicActions;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.fail;

public class BasicElements {
    //Example for Page Title, Text box, Radio Buttons, Text, Header, Image, Logo, Dropdown
    @Test
    public void login_test() throws InterruptedException {
        //Declare the Gecko_Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new ChromeDriver();

        //Go to newtours website
        driver.get("http://newtours.demoaut.com/");

        driver.manage().window().maximize();

        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");
        driver.findElement(By.name("password")).sendKeys("testing");
        driver.findElement(By.name("login")).click();

        //Set explicit wait for 10 seconds by creating object for WebdriverWait class
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));

        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if(driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test passed");
        }   else {
            fail("Test failed");
        }

        //Validate the logo
        WebElement mercuryToursLogo = driver.findElement(By.xpath("//img[@src='/images/nav/logo.gif'][@alt='Mercury Tours']"));
        if(mercuryToursLogo.isDisplayed()) {
            System.out.println("Logo is present");
        } else {
            fail("Logo is not present");
        }

        //Validate the image
        WebElement rentACarImage = driver.findElement(By.xpath("//img[@src='/images/nav/boxad1.gif']"));
        if(rentACarImage.isDisplayed()) {
            System.out.println("Rent a car is present");
        } else {
            fail("Rent a car is not present");
        }

        //Validate header
        //website www.virginia.gov
        /*WebElement header = driver.findElement(By.xpath("//h2[contains(text(), '']"));
        if(header.isDisplayed()) {
            System.out.println("Header is present");
        } else {
            fail("Header is not present");
        }
        the same text bodytext p*/

        //Find the one way radio button
        WebElement radioButton = driver.findElement(By.xpath("//input[@name='tripType'][@value='oneway']"));
        radioButton.click();

        //Find the dropdown
        WebElement passengerDropdown = driver.findElement(By.xpath("//select[@name='passCount']"));
        //Create an object for select dropdown
        Select passengerDropdownSelect = new Select(passengerDropdown);
        //Select any element from select dropdown selectByValue or selectByVisibleText
        passengerDropdownSelect.selectByValue("4");

        //Destroy the driver instance and close the browser
        driver.quit();

    }

}
