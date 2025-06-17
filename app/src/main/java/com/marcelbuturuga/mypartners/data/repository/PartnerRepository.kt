package com.marcelbuturuga.mypartners.data.repository

import com.marcelbuturuga.mypartners.data.api.PartnerApi
import com.marcelbuturuga.mypartners.data.model.Partner
import javax.inject.Inject

class PartnerRepository @Inject constructor(
    private val api: PartnerApi
) {
    suspend fun getPartners(): List<Partner> {
        return api.getPartners()
    }
}