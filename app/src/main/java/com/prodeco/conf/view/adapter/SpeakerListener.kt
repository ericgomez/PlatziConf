package com.prodeco.conf.view.adapter

import com.prodeco.conf.model.Speaker

interface SpeakerListener {
    // Funcion Click
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}