package com.tamerlan.schedulebgtu.presenters.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamerlan.schedulebgtu.Helpers
import com.tamerlan.schedulebgtu.data.DayOfWeek
import com.tamerlan.schedulebgtu.data.Lesson
import com.tamerlan.schedulebgtu.data.TimetableDayOfWeek
import com.tamerlan.schedulebgtu.domain.ParseSchedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {


    private val _listScheduleClass = MutableLiveData<List<TimetableDayOfWeek>?>()
    val listScheduleClass: LiveData<List<TimetableDayOfWeek>?> = _listScheduleClass

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

    fun showSchedule(group: String?) {
        if(group == null ) return

        viewModelScope.launch {
            _listScheduleClass.value = withContext(Dispatchers.Default)
            {
                return@withContext ParseSchedule().getSchedule(group,Helpers().getCurrentStudyPeriod(),"о")
            }
        }
    }
}