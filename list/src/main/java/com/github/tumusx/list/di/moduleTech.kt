package com.github.tumusx.list.di

import com.github.tumusx.list.data.repository.CatsRepositoryImpl
import com.github.tumusx.list.domain.userCase.CatsUserCaseImpl
import com.github.tumusx.list.presenter.CatsState
import com.github.tumusx.list.presenter.viewModel.CatsViewModel
import com.github.tumusx.local.instanceRetrofit.CreateInstanceRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleTech = module {

    factory { CatsRepositoryImpl(CreateInstanceRetrofit.serviceCreate()) }
    single { CatsUserCaseImpl(get()) }

    viewModel {
       CatsViewModel(get())
    }

}