package com.example.coffeeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.coffeeshop.Activity.Domain.ItemsModel
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityDetailBinding
import com.example.coffeeshop.Helper.ManagmentCart

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            tvSmall.setOnClickListener {
            tvSmall.setBackgroundResource(R.drawable.brown_storke_bg)
            tvMedium.setBackgroundResource(0)
            tvLarge.setBackgroundResource(0)
            }

            tvMedium.setOnClickListener {
                tvMedium.setBackgroundResource(R.drawable.brown_storke_bg)
                tvSmall.setBackgroundResource(0)
                tvLarge.setBackgroundResource(0)
            }

            tvLarge.setOnClickListener {
                tvLarge.setBackgroundResource(R.drawable.brown_storke_bg)
                tvSmall.setBackgroundResource(0)
                tvMedium.setBackgroundResource(0)
            }

        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.ivBackground)

            tvTitle.text = item.title
            tvDescriptionText.text = item.description
            tvPrice.text = "$ " + item.price
            tvRating.text = item.rating.toString()

            tvAddtoCart.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    tvNumber.text.toString()
                )
                managmentCart.insertItems(item)
            }

            backBtn.setOnClickListener { finish() }

            tvMais.setOnClickListener {
                tvNumber.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }

            tvMenos.setOnClickListener {
                if (item.numberInCart > 0) {
                    tvNumber.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }

        }
    }
}