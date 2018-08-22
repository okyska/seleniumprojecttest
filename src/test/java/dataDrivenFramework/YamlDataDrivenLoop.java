package dataDrivenFramework;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

//import org.apache.poi.ss.usermodel.WorkbookFactory;

public class YamlDataDrivenLoop {

    @Test
    public void yamlDataDrivenTestLoop() throws IOException, InvalidFormatException, InterruptedException {

        WebDriver driver = null;

        //Create a yaml object
        Yaml yaml = new Yaml();

        //File object
        File file = new File("/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/data.yaml");

        //File config
        File configFile = new File("/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/config.yaml");

        //Create input stream
        FileInputStream fileInputStream = new FileInputStream(file);
        FileInputStream configInputStream = new FileInputStream(configFile);

        //Parse the file and create a series of map of lists
        Map<String, Map<String, Object>> data = (Map<String, Map<String, Object>>) yaml.load(fileInputStream);
        System.out.println(data);

        Map<String, Object> config = (Map<String, Object>) yaml.load(configInputStream);
        System.out.println(config);

        //Create variables for config
        String browserType = config.get("browser").toString();

        //Create an array containing all the records
        String[] recordList = config.get("record").toString().split(",");
        System.out.println(recordList);

        for (String record : recordList) {
            System.out.println(record);
        }
        System.out.println(recordList.length);

        for (int i = 0; i < recordList.length; i++) {

            //Create variables
            String username = data.get(recordList[i]).get("username").toString();
            String password = data.get(recordList[i]).get("password").toString();

            System.out.println(username);
            System.out.println(password);



            //Initialize the selenium webdriver class and create object
            if (browserType.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/geckodriver");
                driver = new FirefoxDriver();
            } else if (browserType.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");
                driver = new ChromeDriver();
            } else if (browserType.equalsIgnoreCase("safari")) {
                System.setProperty("webdriver.safari.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/safaridriver");
                driver = new SafariDriver();
            } else if (browserType.equalsIgnoreCase("ie")) {
                System.setProperty("webdriver.ie.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/iedriver");
                driver = new InternetExplorerDriver();
            }

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
            if (driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
                System.out.println("Test passed");
            } else {
                //fail("Test failed");
                System.out.println("Test failed");
            }

            String tripTipe = "oneway";
            WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripTipe + "\']"));
            trip_type.click();


            driver.quit();


        }

    }
}
