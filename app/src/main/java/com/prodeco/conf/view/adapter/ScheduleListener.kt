package com.prodeco.conf.view.adapter

import com.prodeco.conf.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}