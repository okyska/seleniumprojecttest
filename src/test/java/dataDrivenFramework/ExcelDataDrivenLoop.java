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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static junit.framework.TestCase.fail;

//import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataDrivenLoop {

    @Test
    public void excelDataDrivenTestLoop() throws IOException, InvalidFormatException {

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

        //Count number of row rows in excel sheet
        int firstRow = sheet1.getFirstRowNum();
        System.out.println(firstRow);
        int lastRow = sheet1.getLastRowNum();
        System.out.println(lastRow);

        //Through all the test scenarios
        for (int i = 1; i <= lastRow; i++) {
            //cell variable
            int j = 0;

            //Create variables
            String username = sheet1.getRow(i).getCell(j).getStringCellValue();
            String password = sheet1.getRow(i).getCell(j+1).getStringCellValue();
            String tripType = sheet1.getRow(i).getCell(j+2).getStringCellValue();
            int noOfPassengers = (int) sheet1.getRow(i).getCell(j+3).getNumericCellValue();
            String from = sheet1.getRow(i).getCell(j+4).getStringCellValue();
            String departMonth = sheet1.getRow(i).getCell(j+5).getStringCellValue();
            int departDay = (int) sheet1.getRow(i).getCell(j+6).getNumericCellValue();
            String to = sheet1.getRow(i).getCell(j+7).getStringCellValue();
            String returnMonth = sheet1.getRow(i).getCell(j+8).getStringCellValue();
            int returnDay = (int) sheet1.getRow(i).getCell(j+9).getNumericCellValue();
            String serviceType = sheet1.getRow(i).getCell(j+10).getStringCellValue();
            String airline = sheet1.getRow(i).getCell(j+11).getStringCellValue();


            //Declare the driver path
            System.setProperty("webdriver.chrome.driver", "/Users/oxanaermolenko/IdeaProjects/seleniumprojecttest/chromedriver");

            //Initialize the selenium webdriver class and create object
            WebDriver driver = new ChromeDriver();

            //Go to newtours website
            driver.get("http://newtours.demoaut.com");

            driver.manage().window().maximize();

            //type testing in username text box
            driver.findElement(By.name("userName")).sendKeys(username);
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
                fail("Test failed");
                System.out.println("Test failed");
            }

            //Selecting the trip type
            System.out.println(tripType);
            WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripType + "\']"));
            trip_type.click();
            //Selecting passenger
            WebElement passengerDropdown = driver.findElement(By.xpath("//select[@name='passCount']"));
            Select passengerDropdownSelect = new Select(passengerDropdown);
            passengerDropdownSelect.selectByVisibleText(String.valueOf(noOfPassengers));
            //Selecting depart from
            WebElement fromDropdown= driver.findElement(By.xpath("//select[@name='fromPort']"));
            Select fromDropdownSelect = new Select(fromDropdown);
            fromDropdownSelect.selectByVisibleText(from);
            //Selecting depart month
            WebElement departMonthDropdown = driver.findElement(By.xpath("//select[@name='fromMonth']"));
            Select departMonthDropdownSelect = new Select(departMonthDropdown);
            departMonthDropdownSelect.selectByVisibleText(departMonth);
            //Selecting depart day
            WebElement departDayDropdown = driver.findElement(By.xpath("//select[@name='fromDay']"));
            Select departDayDropdownSelect = new Select(departDayDropdown);
            departDayDropdownSelect.selectByVisibleText(String.valueOf(departDay));
            //Selecting return to
            WebElement toDropdown= driver.findElement(By.xpath("//select[@name='toPort']"));
            Select toDropdownSelect = new Select(toDropdown);
            toDropdownSelect.selectByVisibleText(to);
            //Selecting return month
            WebElement returnMonthDropdown = driver.findElement(By.xpath("//select[@name='toMonth']"));
            Select returnMonthDropdownSelect = new Select(returnMonthDropdown);
            returnMonthDropdownSelect.selectByVisibleText(returnMonth);
            //Selecting return day
            WebElement returnDayDropdown = driver.findElement(By.xpath("//select[@name='toDay']"));
            Select returnDayDropdownSelect = new Select(returnDayDropdown);
            returnDayDropdownSelect.selectByVisibleText(String.valueOf(returnDay));
            //Selecting service type
            WebElement service = driver.findElement(By.xpath("//input[@name='servClass'][@value=\'" + serviceType +"\']"));
            service.click();
            //Selecting airline
            WebElement airlineDropdown = driver.findElement(By.xpath("//select[@name='airline']"));
            Select airlineDropdownSelect = new Select(airlineDropdown);
            airlineDropdownSelect.selectByVisibleText(airline);





            driver.quit();
        }


    }

}
