package com.marcelbuturuga.mypartners.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelbuturuga.mypartners.data.model.Partner
import com.marcelbuturuga.mypartners.data.repository.PartnerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class PartnerListViewModel @Inject constructor(
    private val repository: PartnerRepository
) : ViewModel() {

    private val _groupedPartners = MutableStateFlow<Map<Int, List<Partner>>>(emptyMap())
    val groupedPartners: StateFlow<Map<Int, List<Partner>>> = _groupedPartners.asStateFlow()

    fun loadPartners() {
        viewModelScope.launch {
            try {
                val partners = repository.getPartners()
                _groupedPartners.value = partners.groupBy { it.rating }
            } catch (e: Exception) {
                // TODO: adaugă tratare de erori/logging
            }
        }
    }

    fun removePartner(id: Int) {
        viewModelScope.launch {
            try {
                val partners = repository.removePartner(id)
                _groupedPartners.value = partners.groupBy { it.rating }
            } catch (e : Exception) {
                // TODO
            }
        }

    }


}