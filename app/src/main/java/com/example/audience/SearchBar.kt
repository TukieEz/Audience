package com.example.audience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SearchViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val searching = searchText.map { query ->
        if (query.isBlank()) {
            Cards
        } else {
            Cards.filter {
                if(it is SearchCardAudiences) {
                    it.doesMatchSearchQueryAud(query)
                } else if (it is Persons){
                    it.doesMatchSearchQueryPersons(query)
                } else{
                    false
                }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Cards)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}