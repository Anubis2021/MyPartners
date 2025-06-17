package com.marcelbuturuga.mypartners.data.api

import com.marcelbuturuga.mypartners.data.model.Partner
import retrofit2.http.GET

interface PartnerApi {

    @GET("partners")
    suspend fun getPartners(): List<Partner>
}