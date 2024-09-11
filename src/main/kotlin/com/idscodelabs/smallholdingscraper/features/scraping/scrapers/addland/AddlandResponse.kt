package com.idscodelabs.smallholdingscraper.features.scraping.scrapers.addland

data class AddlandResponse(
    val landCardAdvert: LandCardAdvert,
    val count: Long,
    val items: List<Item>,
    val landTypeResults: List<LandTypeResult>,
    val displayAltNoResultText: Boolean,
    val success: Boolean,
    val errorMessage: Any?,
)

data class LandCardAdvert(
    val identifier: Any?,
    val agentLogo: Any?,
    val agentName: Any?,
    val cta: Any?,
    val image: Any?,
    val subtitle: Any?,
    val text: Any?,
    val title: Any?,
)

data class Item(
    val landId: String,
    val shortId: Long,
    val landType: String,
    val landTypeIcon: String,
    val landCategory: String,
    val landSize: Double,
    val landAgentLogo: LandAgentLogo,
    val accountName: String,
    val images: List<Image>,
    val packageTitle: String,
    val district: String,
    val guidePrice: String,
    val addedDate: String,
    val status: Long,
    val tenure: Long,
    val landTenure: String,
    val statusLabel: String,
    val isFavoriteCurrentUser: Boolean,
    val displayContactAgentButton: Boolean,
    val isProListing: Boolean,
)

data class LandAgentLogo(
    val focalPointLeft: Double,
    val focalPointTop: Double,
    val updateDate: String,
    val altText: String,
    val height: String,
    val width: String,
    val id: Long,
    val key: String,
    val name: String,
    val url: String,
)

data class Image(
    val focalPointLeft: Double,
    val focalPointTop: Double,
    val updateDate: String,
    val altText: String,
    val height: String,
    val width: String,
    val id: Long,
    val key: String,
    val name: String,
    val url: String,
)

data class LandTypeResult(
    val landTypeText: String,
    val landTypeTotalCount: Long,
    val landTypeValue: Long?,
)
