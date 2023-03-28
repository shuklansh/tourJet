package com.shuklansh.tourJet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuklansh.tourJet.Model.Photo
import com.shuklansh.tourJet.RetrofitApi.ApiHelper
import com.shuklansh.tourJet.RetrofitApi.InterfaceApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val apicaller = ApiHelper.getInstance().create(InterfaceApi::class.java)
    //private val _isLoading = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var listofPhotos = listOf<Photo>()
    val randomitem = listOf("New York","Los Angeles","Miami","San Francisco","Delhi","Mumbai","Kolkata","Tokyo","London","Paris","Seoull","Sydney","Spain","USA","United Kingdom")

    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch {
            isLoading.value=true
            listofPhotos = getDatafromApi()
            isLoading.value=false
        }
    }


    suspend fun getDatafromApi() : List<Photo> {
        var index = (0..randomitem.size-1).random()
        val result = apicaller.getImages(randomitem[index],50)
        return result.photos
    }

}