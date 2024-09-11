package com.idscodelabs.smallholdingscraper.features.location.services

import org.springframework.stereotype.Service

@Service
class LocationService {
    fun getLocation(searchTerm: String): Pair<Double, Double> {
        return 0.0 to 0.0
    }
}