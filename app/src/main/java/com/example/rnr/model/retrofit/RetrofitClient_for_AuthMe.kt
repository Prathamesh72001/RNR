package com.example.rnr.model.retrofit

import com.airbnb.lottie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient_for_AuthMe {

    companion object {
        private var retrofit: Retrofit? = null

        @Synchronized
        fun getRetrofit(authToken:String?): Retrofit {
            if(retrofit==null) {
                retrofit = Retrofit.Builder().baseUrl("https://rnr.inspeero.com")
                    .client(OkHttpClient.Builder()
                        .addInterceptor{chain ->
                            chain.proceed(chain.request().newBuilder().also {
                                it.addHeader("Authorization","Bearer $authToken")
                            }.build())
                        }.build())
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    }

}