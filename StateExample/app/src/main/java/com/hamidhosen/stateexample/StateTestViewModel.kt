package com.hamidhosen.stateexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateTestViewModel :ViewModel(){

    private  val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private  val  _surename = MutableLiveData<String>()
    val  surename : LiveData<String> = _surename

    fun  onNameUpdate(newName:String){
        _name.value = newName
    }

    fun  onSureNameUpdate(newName: String){
        _surename.value = newName
    }
}