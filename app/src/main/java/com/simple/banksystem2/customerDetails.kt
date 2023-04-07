package com.simple.banksystem2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.Delegates

class customerDetails : AppCompatActivity() {
    lateinit var nameCustomer: TextView
    lateinit var IdCustomer: TextView
    lateinit var balanceCustomer: TextView
    lateinit var emailCustomer: TextView
    lateinit var transBtn: Button
     var customerUpdateBalance :customerDatabseHelper =customerDatabseHelper(this)

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_details)
        callViews()
        val customerData = intent.getParcelableExtra<Customer>("customer")
        var amount =0
  val amountV =balanceCustomer.text
        transBtn.setOnClickListener {
            val popupMenu = PopupMenu(this@customerDetails, transBtn)
            popupMenu.menuInflater.inflate(R.menu.money, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                // Toast message on menu item clicked
                if (menuItem.title == "50") {
                    amount = customerData?.balance?.plus(50)!!
                } else if (menuItem.title == "100") {
                    amount = customerData?.balance?.plus(100)!!
                } else if (menuItem.title == "200") {
                    amount = customerData?.balance?.plus(200)!!
                }


       if(menuItem.title !=null){
           if (customerData != null) {
               customerUpdateBalance.uodateBalance(Customer(customerData.id,customerData.name,customerData.email,amount))
           }
           balanceCustomer.setText(amount.toString())
           if (customerData != null) {
               Toast.makeText(
                   this@customerDetails,
                   "You Transfer ${customerData.balance} succssefuy",
                   Toast.LENGTH_SHORT
               ).show()
           }
       }

                true
            }

            // Showing the popup menu
            popupMenu.show()
        }
        //balanceCustomer.setText(amount.toString())

        if (customerData != null) {
            nameCustomer.text = customerData.name
            IdCustomer.text = customerData.id.toString()
            balanceCustomer.text = customerData.balance.toString()
            emailCustomer.text = customerData.email

        }
    }

    private fun callViews() {
        nameCustomer = findViewById(R.id.nameView)
        IdCustomer = findViewById(R.id.idView)
        balanceCustomer = findViewById(R.id.balanceView)
        emailCustomer = findViewById(R.id.emailView)
        transBtn = findViewById(R.id.transBtn)

    }
}