package com.example.rnr.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var retrofit: Retrofit? = null

        @Synchronized
        fun getRetrofit(): Retrofit {
            if(retrofit==null) {
                retrofit = Retrofit.Builder().baseUrl("https://rnr.inspeero.com")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    }
}