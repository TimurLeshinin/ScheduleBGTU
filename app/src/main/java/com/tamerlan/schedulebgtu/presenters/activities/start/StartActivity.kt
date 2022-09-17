package com.tamerlan.schedulebgtu.presenters.activities.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tamerlan.schedulebgtu.databinding.ActivityStartBinding
import com.tamerlan.schedulebgtu.presenters.activities.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StartActivity : AppCompatActivity() {

    private val viewModel: StartViewModel by viewModels()
    private lateinit var binding: ActivityStartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)


        setContentView(binding.root)


        binding.btnChooseGroup.setOnClickListener {
            groupChoose()
        }


        binding.btnBack.setOnClickListener{
            back()
        }


        val adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item,
            arrayOf("О-22-МАШ-ирм-Б"))
        binding.spinnerGroups.adapter = adapter

        viewModel.groupsList.observe(this)
        {
            adapter.clear()
            adapter.addAll(it)
        }


        binding.btnNext.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("group", binding.spinnerGroups.selectedItem.toString())
            startActivity(intent)
        }

    }

    private  fun back(){
        binding.linearFacultyChoose.visibility = View.VISIBLE
        binding.linearGroupChoose.visibility = View.GONE
    }

    private fun groupChoose()
    {
        viewModel.chooseFaculty(
            binding.spinnerFaculty.selectedItem.toString(),
            binding.spinnerLevel.selectedItem.toString(),
            binding.spinnerCourse.selectedItem.toString()
        )

        binding.linearGroupChoose.visibility = View.VISIBLE
        binding.linearFacultyChoose.visibility = View.GONE
    }

}