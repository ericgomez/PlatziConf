package com.prodeco.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prodeco.conf.R
import com.prodeco.conf.model.Conference
import com.prodeco.conf.view.adapter.ScheduleAdapter
import com.prodeco.conf.view.adapter.ScheduleListener
import com.prodeco.conf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment(), ScheduleListener {

    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var scheduleViewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        scheduleViewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)

        //Recuperando las vistas en una activity o fragment por medio del ID: rvSchedule en item_schedule.xml
        rvSchedule.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter= scheduleAdapter
        }
        observeViewModel()
    }

    fun observeViewModel(){
        scheduleViewModel.listSchedule.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)
        })

        scheduleViewModel.isLoading.observe(this, Observer<Boolean>  {
            if(it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    // Evento Click
    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        // Navegacion a donde queremos que nos envie el detalle enviando el bundle
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)

    }
}