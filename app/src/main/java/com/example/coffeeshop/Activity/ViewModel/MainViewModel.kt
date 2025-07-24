package com.example.coffeeshop.Activity.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeshop.Activity.Domain.BannerModel
import com.example.coffeeshop.Activity.Domain.CategoryModel
import com.example.coffeeshop.Activity.Repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository= MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }
}