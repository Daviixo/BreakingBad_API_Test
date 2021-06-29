package com.daviixo.breakingbad_api_test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    val itemList = ArrayList<CharacterInfo>()
    lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize adapter
        adapter = CharacterAdapter()
        rvData.adapter = adapter

        // call api to get the data from network
        loadData()
    }

    private fun loadData() {
        // show loading progress bar
        pbLoading.visibility = View.VISIBLE

        ApiManager.getInstance().service.listCharacters()
            .enqueue(object : Callback<List<CharacterInfo>> {

                override fun onResponse(
                    call: Call<List<CharacterInfo>>,
                    response: Response<List<CharacterInfo>>
                ) {
                    val listData: List<CharacterInfo> = response.body()!!

                    // updating data from network to adapter
                    itemList.clear()
                    itemList.addAll(listData)
                    adapter.updateData(itemList)

                    // hide loading progress bar
                    pbLoading.visibility = View.GONE
                }

                override fun onFailure(call: Call<List<CharacterInfo>>, t: Throwable) {
                    // if there is error while get data from network

                    Log.e(TAG, "Error on loading data ${t.message}")
                    pbLoading.visibility = View.GONE
                }

            })
    }
}
