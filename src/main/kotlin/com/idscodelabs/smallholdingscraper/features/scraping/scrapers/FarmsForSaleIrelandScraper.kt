package com.idscodelabs.smallholdingscraper.features.scraping.scrapers

import com.idscodelabs.smallholdingscraper.features.location.model.Country
import com.idscodelabs.smallholdingscraper.features.location.services.LocationService
import com.idscodelabs.smallholdingscraper.features.scraping.JsoupScraper
import com.idscodelabs.smallholdingscraper.features.scraping.Scraper
import com.idscodelabs.smallholdingscraper.features.smallholding.entities.Smallholding
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Component

@Component
class FarmsForSaleIrelandScraper(
    val locationService: LocationService
): JsoupScraper {
    override fun getLink(page: Int): String {
        return if(page == 1) "https://farmsforsaleireland.com/property-type/small-holdings/"
        else "https://farmsforsaleireland.com/property-type/small-holdings/page/$page/"
    }

    override fun getSmallholdingContainers(document: Document): List<Element> {
        return document.getElementsByClass("listing-view").first()?.getElementsByClass("item-wrap")?.toList() ?: listOf()
    }

    override fun newId(element: Element): String {
        val titleNode = element.getElementsByClass("item-title").first()!!.getElementsByTag("a").first()!!
        return "FarmsForSaleIreland_" + titleNode.attr("href")
    }


    override fun getSmallholding(element: Element): Smallholding {
        val titleNode = element.getElementsByClass("item-title").first()!!.getElementsByTag("a").first()!!
        val link = titleNode.attr("href")
        val name = titleNode.text()
        val price = element.getElementsByClass("item-price").first()!!.text().removePrefix("€").replace(",", "").toIntOrNull()?.times(0.84)
        val acres = element.getElementsByClass("h-land-area").first()?.getElementsByTag("span")?.first()?.text()?.toIntOrNull()
        val address = element.getElementsByClass("item-address").first()!!.text()
        val (lat, lng) = locationService.getLocation(address)
        val country = Country.IRELAND
        val addressSplit = address.lowercase().split(",").map { it.trim() }
        val county = addressSplit.firstOrNull { it.startsWith("co.") || it.startsWith("county") }?.removePrefix("co.")?.removePrefix("county")
        val coverImageLink = element.getElementsByClass("listing-featured-thumb").firstOrNull() { it.getElementsByTag("img").first()!!.attr("src").startsWith("https://farmsforsaleireland.com") }?.getElementsByTag("img")?.first()?.attr("src")
        val beds = element.getElementsByClass("h-beds").first()?.getElementsByClass("hz-figure")?.first()?.text()?.toIntOrNull()
        return Smallholding(
            newId(element),
            name,
            acres,
            price,
            lat,
            lng,
            address,
            link,
            country,
            county,
            coverImageLink,
            beds
        )
    }
}