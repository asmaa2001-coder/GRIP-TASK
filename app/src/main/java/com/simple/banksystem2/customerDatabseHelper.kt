package com.simple.banksystem2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class customerDatabseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val KEY_ID = "KEY_ID"
    val KEY_NAME = "KEY_NAME"
    val KEY_EMAIL = "KEY_EMAIL"
    val KEY_BALANCE = "KEY_BALANCE"
    val TABLE_NAME = "CUSTOMERS_DATA"

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "CustomerDatabase"
    }
//CREATE DB FOR FRIST TIME
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CUSTOMER_TABLE = ("CREATE TABLE $TABLE_NAME" +
                "($KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_NAME TEXT," +
                "$KEY_EMAIL TEXT," +
                "$KEY_BALANCE INT)")
        db?.execSQL(CREATE_CUSTOMER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME)
        onCreate(db)
    }
//IF I WANT TO ADD NEW CUSTOMER FOR MY DB
    fun addCustomer(customer: Customer): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, customer.id)
        contentValues.put(KEY_NAME, customer.name)
        contentValues.put(KEY_EMAIL, customer.email)
        contentValues.put(KEY_BALANCE, customer.balance)
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }
    //TO GEL ALL MY CUSTOMERS TO DISPLAY THEM IN RECYCLEVIEW
    fun getAllItems(): ArrayList<Customer> {
        val itemList = ArrayList<Customer>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val item = Customer(
                    cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(KEY_BALANCE))

                )
                itemList.add(item)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return itemList
    }
//TO MAKE IT EASY TO UPDATE AMOUNT OF MONEY
    fun uodateBalance(customer: Customer){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_BALANCE,customer.balance)
        contentValues.put(KEY_NAME,customer.name)
        contentValues.put(KEY_ID,customer.id)
        contentValues.put(KEY_EMAIL,customer.email)
        val success = db.update(TABLE_NAME,contentValues,"KEY_ID="+customer.id,null)
        db.close()
    }
}






