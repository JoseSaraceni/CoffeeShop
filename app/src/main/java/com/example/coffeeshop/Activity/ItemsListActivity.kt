package com.example.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeeshop.Activity.Adapter.itemListCategoryAdapter
import com.example.coffeeshop.Activity.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {
    lateinit var binding : ActivityItemsListBinding
    private val viewModel= MainViewModel()
    private var id: String=""
    private var title: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundles()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar.visibility= View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemsListActivity, Observer {
                rvList.layoutManager=
                    GridLayoutManager(this@ItemsListActivity,2)
                rvList.adapter= itemListCategoryAdapter(it)
                progressBar.visibility= View.GONE
            })
            iconBack.setOnClickListener { finish() }
        }
    }

    private fun getBundles(){
        id=intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!

        binding.tvList.text= title
    }
}