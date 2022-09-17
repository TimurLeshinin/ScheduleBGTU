package com.tamerlan.schedulebgtu.presenters.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tamerlan.schedulebgtu.R
import com.tamerlan.schedulebgtu.databinding.FragmentHomeBinding
import com.tamerlan.schedulebgtu.presenters.activities.main.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel:HomeViewModel by viewModels()
    private val dataModel:DataViewModel by activityViewModels()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView =  _binding!!.timetable
        homeViewModel.showSchedule(dataModel.getGroup())
        homeViewModel.listScheduleClass.observe(viewLifecycleOwner) {

            if(it != null)
                listView.adapter = AdapterDOW(requireContext(), it)
        };

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


