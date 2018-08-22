package dataDrivenFramework;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

//import org.apache.poi.ss.usermodel.WorkbookFactory;

public class YamlDataDriven {

    @Test
    public void yamlDataDrivenTest() throws IOException, InvalidFormatException, InterruptedException {

        //Create a yaml object
        Yaml yaml = new Yaml();

        //File object
        File file = new File("/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/data.yaml");

        //Create input stream
        FileInputStream fileInputStream = new FileInputStream(file);

        //Parse the file and create a series of map of lists
        Map<String, Map<String, Object>> data = (Map<String, Map<String, Object>>) yaml.load(fileInputStream);
        System.out.println(data);


        //Create variables
        String username = data.get("record1").get("username").toString();
        String password = data.get("record1").get("password").toString();

        System.out.println(username);
        System.out.println(password);

        //Declare the driver path
        System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new ChromeDriver();

        //Go to newtours website
        driver.get("http://newtours.demoaut.com/");

        driver.manage().window().maximize();

        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys(username);
        System.out.println();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        //Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='findFlights']")));

        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if(driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test passed");
        }   else {
            //fail("Test failed");
            System.out.println("Test failed");
        }

        String tripTipe = "oneway";
        WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripTipe + "\']"));
        trip_type.click();


        driver.quit();


    }

}
