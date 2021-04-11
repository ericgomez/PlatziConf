package com.prodeco.conf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.prodeco.conf.model.Conference
import com.prodeco.conf.model.Speaker

const val CONFERENCES_COLLECTION_NAME = "conferences" //'conferences' debe ser el nombre exacto de la DB
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService{
    //Conexion directa con la base de datos
    val firebaseFirestore = FirebaseFirestore.getInstance()
    //Obtener o descargar datos en modo Online
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        //Datos persistentes si no hay conexion la siguiente vez
        firebaseFirestore.firestoreSettings = settings
    }

    //Funciones para acceder a la informacion
    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
                .orderBy("category")//Ordenar Datos por Categoria
                .get()//Obtener la informacion
                .addOnSuccessListener { result ->
                    for(doc in result){
                        val list = result.toObjects(Speaker::class.java)
                        callback.onSuccess(list)//Enviamos los resultados
                        break
                    }
                }//Llamada de resultados

    }

    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
                .get()//Obtener la informacion
                .addOnSuccessListener { result ->
                    for(doc in result){
                        val list = result.toObjects(Conference::class.java)
                        callback.onSuccess(list)//Enviamos los resultados
                        break
                    }
                }//Llamada de resultados
    }
}