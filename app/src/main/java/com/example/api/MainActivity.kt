package com.example.api

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
     lateinit var recycle:RecyclerView
     lateinit var myadapter: myAdpater
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle = findViewById(R.id.rec)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
          val retrofitData = retrofitBuilder.getProductData()
          retrofitData.enqueue(object : Callback<myData?> {
              override fun onResponse(call: Call<myData?>?, response: Response<myData?>?) {
                  //API is not failure
                 var reponseBody = response?.body()
                  val productlist = reponseBody?.products!!
                   myadapter = myAdpater(this@MainActivity,productlist)
                   recycle.adapter= myadapter
                  recycle.layoutManager = LinearLayoutManager(this@MainActivity)
              }

              override fun onFailure(call: Call<myData?>?, t: Throwable?) {
                 //if API is failure
                  Log.d("Main Activity","OnFailure"+ t?.message)
              }
          })

    }
}