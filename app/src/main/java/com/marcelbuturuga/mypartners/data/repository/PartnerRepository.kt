package com.marcelbuturuga.mypartners.data.repository

import com.marcelbuturuga.mypartners.data.api.PartnerApi
import com.marcelbuturuga.mypartners.data.model.Partner
import java.util.ArrayList
import javax.inject.Inject

class PartnerRepository @Inject constructor(
    private val api: PartnerApi
) {

    private var partners: List<Partner> = emptyList()

    suspend fun getPartners(): List<Partner> {
        partners = api.getPartners()
        return partners
    }

    fun markAsRemoved(id: Int): List<Partner> {
        partners = partners.map {
            if (it.id == id) it.copy(isRemoved = true) else it
        }
        return partners
    }
}