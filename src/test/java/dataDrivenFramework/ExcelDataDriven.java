package dataDrivenFramework;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.*;

import static junit.framework.TestCase.fail;

public class ExcelDataDriven {

    @Test
    public void excelDataDrivenTest() throws IOException, InvalidFormatException, InterruptedException {

        //Create dataFile from File class with excel data
        File dataFile = new File("/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/Data.xlsx");

        //Load file using InputStream class
        FileInputStream inputStream = new FileInputStream(dataFile);

        //Create a buffer object for the input stream
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        //Load workbook
        Workbook workbook = WorkbookFactory.create(bufferedInputStream);

        //Create an object for the sheet
        Sheet sheet1 = workbook.getSheet("Sheet1");

        //Create variables
        String username = sheet1.getRow(1).getCell(0).getStringCellValue();
        String password = sheet1.getRow(1).getCell(1).getStringCellValue();

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

        //wait for 5 secs
        Thread.sleep(7000);

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
