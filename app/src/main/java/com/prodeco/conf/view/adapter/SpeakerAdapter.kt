package com.prodeco.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.prodeco.conf.R
import com.prodeco.conf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    var listSpeaker = ArrayList<Speaker>()
    //Metodo para crear el diseño autilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent,false))//Indicar cual es archvio que necesitamos utilizar para el diseño de un item

    //Cuantos elementos tenemos
    override fun getItemCount() = listSpeaker.size

    //Como enlazar los elementos
    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        val speaker = listSpeaker[position] as Speaker

        //Obtenemos los datos de la Class ViewHolder
        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace

        //Utilizamos la API de la dependencia: Glide para descargar imagenes
        Glide.with(holder.itemView.context)
                .load(speaker.image)//Cargar la imagen
                .apply(RequestOptions.circleCropTransform())//trasnformar la imagen a circular
                .into(holder.ivSpeakerImage)//Donde se colocara

        //Evento Click
        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClicked(speaker, position)
        }

    }

    fun updateData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    //Datos a cargar obtenidos desde la carpeta Layoud de los archivos item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)//obtenemos el resultado por medio del id
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvItemSpeakerWork)
        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)
    }

}