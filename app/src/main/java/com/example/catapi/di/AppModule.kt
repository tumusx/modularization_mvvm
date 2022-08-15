package com.example.catapi.di

import com.example.catapi.common.Const.BASE_URL
import com.example.catapi.data.network.api.CatsApi
import com.example.catapi.data.repository.CatsRepositoryImpl
import com.example.catapi.domain.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton

    fun provideApi(): CatsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(CatsApi::class.java)
    }



    @Provides
    @Singleton
    fun provideCatsRepository(api: CatsApi): CatsRepository {
        return CatsRepositoryImpl(api)
    }

}