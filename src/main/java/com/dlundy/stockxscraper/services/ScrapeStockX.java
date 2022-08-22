package com.dlundy.stockxscraper.services;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.Duration;
import java.util.List;

public class ScrapeStockX {

    private static final String OUTPUT_FILE = "jordan-1-urls.csv";

    public static void openStockX() throws IOException {

        // set driver location (within project folder)
        System.setProperty("webdriver.gecko.driver", "./geckodriver");

        // instantiate a ChromeDriver class
        WebDriver driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webDriver -> "complete".equals(((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")));

        // maximize the browser
        driver.manage().window().maximize();

        // launch website
        driver.get("https://stockx.com/retro-jordans/air-jordan-1/size-10");
        //refresh the page elements
        driver.navigate().refresh();
        Writer writer = new FileWriter(OUTPUT_FILE);
        //wait for the page number element to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.css-12da55z-PaginationButton:nth-child(7)")));
        //make a list of page numbers
        List<WebElement> pages = driver.findElements(By.cssSelector("a.css-12da55z-PaginationButton:nth-child(7)"));
        //if the element is present, the list size is greater than 0
        if(pages.size() > 0){
            //parse the text as an integer
            int totalPages = Integer.parseInt(pages.get(0).getText());
            //print the number of pages to console
            System.out.println("Total Pages: " + totalPages);
            //loop through all the pages
            for(int i = 1; i <= totalPages; i++){
                driver.get("https://stockx.com/retro-jordans/air-jordan-1/size-10?page=" + i);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.css-1ibvugw-GridProductTileContainer:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > img:nth-child(1)")));
                //Put all the products in a list
                List<WebElement> products = driver.findElements(By.className("css-1ibvugw-GridProductTileContainer"));
                System.out.println(products.size());
                //Loop through all the products
                for(int j = 1; j <= products.size(); j++){
                    //Get the product url
                    String link = driver.findElement(By.cssSelector("div.css-1ibvugw-GridProductTileContainer:nth-child(" + j + ") > div:nth-child(1) > a:nth-child(1)")).getAttribute("href");
                    System.out.println(link);
                    //write the product url to csv
                    writer.write(link + '\n');
                    // flush the writer
                    writer.flush();
                }
            }
            //close the driver
            driver.close();
        }


    }
}
