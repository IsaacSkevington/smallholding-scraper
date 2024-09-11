package com.idscodelabs.smallholdingscraper.features.smallholding.repository

import com.idscodelabs.smallholdingscraper.features.smallholding.entities.Smallholding
import org.springframework.data.repository.CrudRepository

interface SmallholdingRepository: CrudRepository<Smallholding, String>