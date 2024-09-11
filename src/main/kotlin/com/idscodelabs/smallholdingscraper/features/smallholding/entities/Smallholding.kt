package com.idscodelabs.smallholdingscraper.features.smallholding.entities

import com.idscodelabs.smallholdingscraper.features.location.model.Country
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id


@Entity
class Smallholding(

    @Id val id: String,

    val name: String,
    val acres: Int?,
    val price: Double?,
    val lat: Double,
    val lng: Double,
    val address: String,
    val link: String,

    @Enumerated(EnumType.STRING)
    val country: Country,

    val county: String?,
    val coverImageLink: String?,
    val beds: Int?,
)