package com.dlundy.stockxscraper;

import com.dlundy.stockxscraper.services.ScrapeStockX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockxScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockxScraperApplication.class, args);

        ScrapeStockX.openStockX();
    }

}
