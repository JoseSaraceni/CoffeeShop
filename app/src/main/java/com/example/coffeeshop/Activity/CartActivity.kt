package com.example.coffeeshop.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.Activity.Adapter.CartAdapter
import com.example.coffeeshop.Helper.ChangeNumberItemsListener
import com.example.coffeeshop.Helper.ManagmentCart
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managmentCart: ManagmentCart
    private var tax: Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        calculateCart()
        setVariable()
        initCartList()
        initBottomMenu()


    }

    private fun initBottomMenu() {
        binding.tvCart.setOnClickListener {
            startActivity(Intent(this, CartActivity :: class.java))
        }
    }

    private fun initCartList() {
        binding.apply {
            rvList.layoutManager= LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL,false)
            rvList.adapter= CartAdapter(
                managmentCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculateCart()
                    }
                }
            )
        }
    }

    private fun setVariable() {
        binding.btnBack.setOnClickListener { finish() }
    }

    private fun calculateCart() {
        val percenTax=0.02
        val delivery=15
        tax=((managmentCart.getTotalFee()*percenTax)*100)/100.0
        val total=((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemtotal = (managmentCart.getTotalFee()*100)/100
        binding.apply {
            tvTotalPrice.text="$$itemtotal"
            tvTotalTexPrice.text="$$tax"
            tvDeliveryPrice.text="$$delivery"
            tvTotalPrice.text="$$total"
        }
    }
}