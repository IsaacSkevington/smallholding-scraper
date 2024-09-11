package com.idscodelabs.smallholdingscraper.features.scraping

import com.idscodelabs.smallholdingscraper.common.logging.logger
import com.idscodelabs.smallholdingscraper.features.smallholding.entities.Smallholding
import com.idscodelabs.smallholdingscraper.features.smallholding.repository.SmallholdingRepository
import org.springframework.data.repository.findByIdOrNull

interface Scraper<ScraperDocument, ScraperElement> {

    fun getDocument(link: String): ScraperDocument
    fun getLink(page: Int): String


    fun scrape(smallholdingRepository: SmallholdingRepository): List<Smallholding>{
        logger().info("Scraping data")
        var page = 1
        val smallholdings = mutableListOf<Smallholding>()
        while(true) {
            val new = try {
                val link = getLink(page)
                logger().info("Attempting connection to $link")
                scrape(link, smallholdingRepository)
            } catch (e: Throwable) {
                e.printStackTrace()
                logger().info("Connection failed on page $page")
                break
            }
            smallholdings.addAll(new)
            page += 1
        }
        return smallholdings
    }

    fun getSmallholdingContainers(document: ScraperDocument): List<ScraperElement>
    fun getSmallholding(element: ScraperElement): Smallholding
    fun newId(element: ScraperElement): String

    fun scrape(link: String, smallholdingRepository: SmallholdingRepository): List<Smallholding>{
        logger().info("Scraping data")
        logger().info("Getting containers")
        val elements = getSmallholdingContainers(getDocument(link))
        logger().info("${elements.size} smallholding containers found")
        val newSmallholdings = elements.mapNotNull {
            val id = newId(it)
            logger().info("Finding smallholding with id $id")

            smallholdingRepository.findByIdOrNull(id)?.let {
                logger().info("Smallholding already exists")
                return@mapNotNull null
            }
            try {
                logger().info("Getting smallholding")
                getSmallholding(it)
            }catch (e: Throwable){
                e.printStackTrace()
                null
            }
        }
        logger().info("Adding ${newSmallholdings.size} smallholdings to db")
        return newSmallholdings
    }

    
}