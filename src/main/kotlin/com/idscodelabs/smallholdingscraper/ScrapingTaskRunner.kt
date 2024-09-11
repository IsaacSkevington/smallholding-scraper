package com.idscodelabs.smallholdingscraper

import com.idscodelabs.smallholdingscraper.common.logging.logger
import com.idscodelabs.smallholdingscraper.features.scraping.ScrapingService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ScrapingTaskRunner(
    val scrapingService: ScrapingService
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        logger().info("Running scraping task")
        scrapingService.scrapeAll()
    }
}