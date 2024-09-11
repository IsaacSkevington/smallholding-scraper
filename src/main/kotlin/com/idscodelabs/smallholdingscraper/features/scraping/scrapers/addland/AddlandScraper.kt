package com.idscodelabs.smallholdingscraper.features.scraping.scrapers.addland

import com.idscodelabs.smallholdingscraper.features.scraping.SeleniumScraper
import com.idscodelabs.smallholdingscraper.features.smallholding.entities.Smallholding
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class AddlandScraper: SeleniumScraper<AddlandResponse, Item> {

    override val responseFilterUrl: String = "https://addland.com/umbraco/Api/MapApi/GetMapMarkersList"
    override fun getLink(page: Int): String {
        if(page > 1) throw Exception()
        return "https://addland.com/land-search/wales/?center=-3.60650011&center=52.368489644&placeType=Region&query=wales&radius=0&dropdowns=0&showProListingsOnly=false"
    }

    override val responseClass: KClass<AddlandResponse> = AddlandResponse::class

    override fun getSmallholding(element: Item): Smallholding {
        TODO("Not yet implemented")
    }

    override fun getSmallholdingContainers(document: AddlandResponse): List<Item> {
        return document.items
    }

    override fun newId(element: Item): String {
        TODO("Not yet implemented")
    }


}