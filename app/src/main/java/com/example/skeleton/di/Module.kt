package com.example.skeleton.di


import com.example.skeleton.datasource.remote.signin.SignInRemoteDataSource
import com.example.skeleton.datasource.remote.signin.SignInRemoteDataSourceImpl
import com.example.skeleton.datasource.room.account.AccountRoomDataSource
import com.example.skeleton.datasource.sharedpre.BaseSharedPrefDataSource
import com.example.skeleton.datasource.sharedpre.BaseSharedPrefDataSourceImpl
import com.example.skeleton.domain.usecase.SignInUseCase
import com.example.skeleton.features.sigin.viewModel.SignInViewModel
import com.example.skeleton.model.ktor.Service
import com.example.skeleton.repository.siginin.SignInRepository
import com.example.skeleton.repository.siginin.SignInRepositoryImpl
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    single { Service.getKoinApi() }
}
val accountRoomDataSource = module {
    single { AccountRoomDataSource() }
}
val baseSharedPrefDataSource = module {
    single<BaseSharedPrefDataSource> { BaseSharedPrefDataSourceImpl() }
}

val signIn = module {
    viewModel { SignInViewModel(get()) }
    factory { SignInUseCase(get()) }
    factory<SignInRepository> { SignInRepositoryImpl(get(), get(), get()) }
    factory<SignInRemoteDataSource> { SignInRemoteDataSourceImpl(get()) }
}