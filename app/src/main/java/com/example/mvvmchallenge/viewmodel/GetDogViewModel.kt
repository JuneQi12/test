package com.example.mvvmchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmchallenge.model.Repository
import com.example.mvvmchallenge.model.data.GetDogResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class GetDogViewModel @Inject constructor(private val repository:Repository):ViewModel() {
    val dogLiveData = MutableLiveData<GetDogResponse>()

    fun getDogInfo(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getDog()
                if(response.isSuccessful){
                    response.body().let {
                       dogLiveData.postValue(it)
                    }
                }else {
                    Log.e("api","api failed")
                }
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}