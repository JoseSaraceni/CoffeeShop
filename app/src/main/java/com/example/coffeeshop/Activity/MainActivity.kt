package com.example.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeeshop.Activity.Adapter.CategoryAdapter
import com.example.coffeeshop.Activity.Adapter.PopularAdapter
import com.example.coffeeshop.Activity.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel= MainViewModel()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initPopular()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.loadPopular().observeForever {
            binding.rvPopular.layoutManager= GridLayoutManager(this,2)
            binding.rvPopular.adapter= PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        }
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility= View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.rvCategory.layoutManager= LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,false
            )

            binding.rvCategory.adapter= CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .centerCrop()
                .into(binding.banner)

            binding.progressBarBanner.visibility = View.GONE
        }
        viewModel.loadBanner()
    }
}
