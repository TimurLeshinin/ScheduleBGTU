package com.tamerlan.schedulebgtu.presenters.activities.start

import android.R
import android.icu.util.Calendar
import android.os.Handler
import android.provider.ContactsContract
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamerlan.schedulebgtu.Helpers
import com.tamerlan.schedulebgtu.domain.ParseSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(): ViewModel(){


    val startMainActivity  = MutableLiveData<Boolean>().apply {
        value = false
    }


    val groupsList = MutableLiveData<List<String>>().apply {
        emptyList<String>()
    }


    init {
        viewModelScope.launch {
//            text.value = withContext(Dispatchers.Default)
//            {
//               var a =  ParseSchedule()
//
//                return@withContext  a.getFaculty().toString()
//            }.toString()
        }

    }

    fun chooseFaculty(faculty: String, level:String, course:String)
    {
        viewModelScope.launch {
            groupsList.value = withContext(Dispatchers.Default)
            {

                try {
                    val helpers = Helpers()
                    return@withContext ParseSchedule().getGroups(
                        faculty,
                        level,
                        helpers.getCurrentStudyPeriod(),
                       //TODO() select form
                        "очная",
                        helpers.getGroupNumberOfCourse(Integer.parseInt(course.substring(0,1)))
                    )
                }
                catch (e:Exception)
                {
                    return@withContext  emptyList<String>()
                }
            }
        }


    }




}