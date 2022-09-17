package com.tamerlan.schedulebgtu.presenters.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DataViewModel @Inject constructor(): ViewModel() {
    private val group = MutableLiveData<String>()

    fun setGroup(groupName: String) {
        group.value = groupName
    }

    fun getGroup() = group.value

}