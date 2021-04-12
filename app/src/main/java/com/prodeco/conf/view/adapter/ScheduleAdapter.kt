package com.prodeco.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prodeco.conf.R
import com.prodeco.conf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()
    //Metodo para crear el diseño autilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent,false))//Indicar cual es archvio que necesitamos utilizar para el diseño de un item

    //Cuantos elementos tenemos
    override fun getItemCount() = listConference.size

    //Como enlazar los elementos
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        //Seteamos la hora al formato requerido
        val hourFormat = simpleDateFormat.format(conference.datetime)

        //Mostrar en textView
        holder.tvConferenceHour.text = hourFormat
        holder.tvConferenceAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()//Esto nos mostrara si es AM o PM y en Mayusculas

        holder.itemView.setOnClickListener{
            scheduleListener.onConferenceClicked(conference, position)
        }
    }

    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    //Datos a cargar
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }

}