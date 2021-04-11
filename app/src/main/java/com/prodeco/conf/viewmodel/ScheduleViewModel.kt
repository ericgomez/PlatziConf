package com.prodeco.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodeco.conf.model.Conference
import com.prodeco.conf.network.Callback
import com.prodeco.conf.network.FirestoreService

class ScheduleViewModel : ViewModel(){
    val firestoreService = FirestoreService() //Creamos una instacia de la clase FirestoreService
    val listSchedule: MutableLiveData<List<Conference>> = MutableLiveData() //lista de nuestro cronograma
    val isLoading =MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}