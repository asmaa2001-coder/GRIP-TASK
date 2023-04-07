package com.simple.banksystem2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simple.banksystem2.databinding.ActivityMainBinding

class CustomerActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var customerDB: customerDatabseHelper
    lateinit var ourRecycle: RecyclerView
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        binding = ActivityMainBinding.inflate(layoutInflater)
//THIS ACTIVITY TO HELP ME TO DISPLAY CUSTOMERS :)
        //  setContentView(binding.root)
        customerDB = customerDatabseHelper(applicationContext)
        ourRecycle = findViewById(R.id.recycleItems)
        ourRecycle.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(customerDB.getAllItems())
        ourRecycle.adapter = adapter

        // Setting the Adapter with the recyclerview
        adapter.itemOnClick = {
            val i = Intent(this, customerDetails::class.java)
            i.putExtra("customer", it)
            startActivity(i)
            // addNewCustomer()

        }
       //  addCost()
    }

    private fun addCost() {
        customerDB.addCustomer(Customer(1, "ASMAA", "ASMAA@gmail.com", 10000))
        customerDB.addCustomer(Customer(2, "SALLY", "SALLY@gmail.com", 10000))
        customerDB.addCustomer(Customer(3, "ALIA", "ALIA@gmail.com", 10000))
        customerDB.addCustomer(Customer(4, "shad", "shad@gmail.com", 10000))
        customerDB.addCustomer(Customer(5, "said", "said@gmail.com", 300))
        customerDB.addCustomer(Customer(6, "fahd", "fahd@gmail.com", 1793))
        customerDB.addCustomer(Customer(7, "nadin", "nadin@gmail.com", 3574))
        customerDB.addCustomer(Customer(8, "lili", "lili@gmail.com", 5734))
        customerDB.addCustomer(Customer(9, "sqara", "sqara@gmail.com", 9283))
        customerDB.addCustomer(Customer(10, "khan", "khan@gmail.com", 928))

    }
}