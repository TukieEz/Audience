package com.example.audience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class SearchBar: ViewModel() {
    private val _searchtext = MutableStateFlow("")
    val searchtext = _searchtext.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searching = MutableStateFlow(Cards)
    val searching = searchtext
        .combine(_searching) { text, searching ->
            if (text.isBlank()) {
                searching
            } else {
                searching.filter {
                    it.doesMathSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _searching.value
        )
    fun onSearchTextChange(text: String){
        _searchtext.value = text
    }
}