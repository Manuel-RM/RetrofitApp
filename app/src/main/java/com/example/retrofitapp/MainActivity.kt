package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitapp.remote.PokemonEntry
import com.example.retrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("30")

       retrofit.enqueue(object: Callback<PokemonEntry>{
           override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>){
               val resBody = response.body()
               if (resBody != null){
                   Log.d("retrofitresponse", "res: ${resBody}")
                   Log.d("retrofitresponse", "name: ${resBody.name}")
                   val stats = response.body()?.stats
                   for (stat in resBody.stats){
                       Log.d("retrofitresponse", "${stat.stat.stat_value}: ${stat.base_stat}")
                   }
                   Log.d("retrofitresponse", "type: ${resBody.types[0].type.name}")
                   Log.d("retrofitresponse", "sprites ${resBody.sprites.front_default}")
               }
           }
            override fun onFailure(call: Call<PokemonEntry>, t: Throwable){
                Log.e("retrofitresponse", "error: ${t.message}")
            }
        })
    }
}