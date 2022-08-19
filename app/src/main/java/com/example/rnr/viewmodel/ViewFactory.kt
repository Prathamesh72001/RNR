package com.example.rnr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MyViewModel::class.java)){
            MyViewModel() as T
        }
        else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}