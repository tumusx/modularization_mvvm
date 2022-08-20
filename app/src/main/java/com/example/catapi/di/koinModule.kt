package com.example.catapi.di

import android.app.Application
import androidx.room.Room
import com.example.catapi.common.Const
import com.example.catapi.data.network.api.CatsApi
import com.example.catapi.data.network.local.dao.CatsDAO
import com.example.catapi.data.network.local.database.CatsDatabase
import com.example.catapi.data.repository.CatsRepositoryImpl
import com.example.catapi.data.repository.SaveCatsRepository
import com.example.catapi.domain.userCase.CatsUserCaseImpl
import com.example.catapi.domain.userCase.SaveCatsUseCaseImpl
import com.example.catapi.presenter.viewModel.CatsViewModel
import com.example.catapi.presenter.viewModel.FavoriteStateCatsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val moduleTech = module {

    fun provideDatabase(application: Application): CatsDatabase {
        return Room.databaseBuilder(application, CatsDatabase::class.java, "cats_save")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    fun provideDao(database: CatsDatabase) : CatsDAO = database.catsDao()

    single { provideDao(get()) }
    single { provideDatabase(androidApplication()) }

    factory {
        Retrofit.Builder().baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(CatsApi::class.java)
    }

    factory { CatsRepositoryImpl(get()) }
    factory { SaveCatsRepository(get()) }
    single { SaveCatsUseCaseImpl(get()) }
    single { CatsUserCaseImpl(get())}

    viewModel {
        CatsViewModel(get())
    }
    viewModel {
        FavoriteStateCatsViewModel(get())
    }

}