package com.github.tumusx.local.instanceRetrofit

import com.github.tumusx.local.service.CatsApi
import com.github.tumusx.shared.constants.Const.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object CreateInstanceRetrofit{
    fun serviceCreate() : CatsApi {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create()
    }
}