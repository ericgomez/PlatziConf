package com.prodeco.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prodeco.conf.model.Speaker
import com.prodeco.conf.network.Callback
import com.prodeco.conf.network.FirestoreService

class SpeakersViewModel : ViewModel(){
    val firestoreService = FirestoreService() //Creamos una instacia de la clase FirestoreService
    val listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData() //lista de nuestro cronograma
    val isLoading =MutableLiveData<Boolean>()//Saber si termino el proceso

    fun refresh(){
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase(){
        // Importamos el Callback desde network
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            // Obtenemos los metodos desde firestoreService en networ
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
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