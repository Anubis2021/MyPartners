package com.marcelbuturuga.mypartners.data.repository

import com.marcelbuturuga.mypartners.data.api.PartnerApi
import com.marcelbuturuga.mypartners.data.model.Partner
import java.util.ArrayList
import javax.inject.Inject

class PartnerRepository @Inject constructor(
    private val api: PartnerApi
) {

    private var _partner: List<Partner> = ArrayList<Partner>();

    suspend fun getPartners(): List<Partner> {
//         api.getPartners()

        _partner = api.getPartners()

        return _partner

    }

    suspend fun removePartner(id: Int): List<Partner> {

        return _partner.filter {
            it.id == id
        }
    }
}