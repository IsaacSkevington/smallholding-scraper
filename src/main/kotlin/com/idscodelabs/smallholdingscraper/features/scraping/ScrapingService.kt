package com.idscodelabs.smallholdingscraper.features.scraping

import com.idscodelabs.smallholdingscraper.common.logging.logger
import com.idscodelabs.smallholdingscraper.features.smallholding.entities.Smallholding
import com.idscodelabs.smallholdingscraper.features.smallholding.repository.SmallholdingRepository
import org.jsoup.Jsoup
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.log

@Service
class ScrapingService(
    private val scrapers: List<Scraper<*, *>>,
    private val smallholdingRepository: SmallholdingRepository
) {



    @Transactional
    fun scrapeAll(){
        logger().info("Scraping smallholdings")
        val newSmallholdings = scrapers.flatMap {
            it.scrape(smallholdingRepository)
        }
        logger().info("Smallholdings scraped")
        smallholdingRepository.saveAll(newSmallholdings)
    }
}