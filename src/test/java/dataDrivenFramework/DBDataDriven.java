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

import javax.xml.transform.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

//import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DBDataDriven {

    //Create variables for database connection
    private final static String dbUrl = "jdbc:postgresql://localhost:5432/company";
    private final static String user = "seleniumguru";
    private final static String password = "test1234";

    @Test
    public void dbDataDrivenTestLoop() throws IOException, InvalidFormatException, InterruptedException, SQLException, ClassNotFoundException {

        //Use class for postgres
        Class.forName("org.postgresql.Driver");

        //Create an object

        //create a connection object
        Connection connection = DriverManager.getConnection(dbUrl, user, password);

        //Create a statement
        Statement statement = connection.createStatement();

        //Create a result set
        ResultSet resultSet = statement.executeQuery("select * from customer inner join flight_details on (customer.id=flight_details.id);");


        WebDriver driver = null;
        String browserType = "chrome";

        while(resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");


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
                driver.get("http://newtours.herokuapp.com/");

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

