package com.example.maytheforcebewith.ui.main.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.maytheforcebewith.base.model.BaseViewModel
import com.example.maytheforcebewith.base.model.PeopleData

class PeopleItemViewModel : BaseViewModel(){
    private val characterNameLiveData = MutableLiveData<String>()


    fun bind(peopleData: PeopleData) {
        characterNameLiveData.postValue(peopleData.name)
    }

    fun getCharacterName(): LiveData<String> {
        return characterNameLiveData
    }
}