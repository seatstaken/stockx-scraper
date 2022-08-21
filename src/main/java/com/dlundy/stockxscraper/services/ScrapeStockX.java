package com.dlundy.stockxscraper.services;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ScrapeStockX {

    public static void openStockX(){

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
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.css-12da55z-PaginationButton:nth-child(7)")));
        List<WebElement> pages = driver.findElements(By.cssSelector("a.css-12da55z-PaginationButton:nth-child(7)"));
        if(pages.size() > 0){
            int totalPages = Integer.parseInt(pages.get(0).getText());
            System.out.println("Total Pages: " + totalPages);
            for(int i = 1; i < totalPages; i++){
                driver.get("https://stockx.com/retro-jordans/air-jordan-1/size-10?page=" + i);

            }

            driver.close();
        }


    }
}
