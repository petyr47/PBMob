package com.aneke.peter.pbmob.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aneke.peter.pbmob.network.model.SearchImageResponse
import com.aneke.peter.pbmob.repository.SearchRepository
import com.aneke.peter.pbmob.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: SearchRepository) : ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.OPENED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "fruits")
    val searchTextState: State<String> = _searchTextState

    private val _searchResults: MutableState<Resource<SearchImageResponse>?> =
        mutableStateOf(value = null)
    val searchResults: State<Resource<SearchImageResponse>?> = _searchResults

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun search() {
        val query = searchTextState.value
        _searchResults.value = Resource.loading()
        viewModelScope.launch {
            val result = repository.search(query)
            if (result.success) {
                _searchResults.value = Resource.success(result, result.message)
            } else {
                _searchResults.value = Resource.error(result.message)
            }
        }
    }





}