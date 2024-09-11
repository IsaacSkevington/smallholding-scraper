package com.idscodelabs.smallholdingscraper.features.scraping

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

interface JsoupScraper: Scraper<Document, Element> {
    companion object {
        val userAgentHeader =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"
    }

    override fun getDocument(link: String): Document {
        return Jsoup.connect(link).userAgent(userAgentHeader).get()
    }


}