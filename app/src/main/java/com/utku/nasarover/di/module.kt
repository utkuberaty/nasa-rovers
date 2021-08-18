package com.utku.nasarover.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.utku.nasarover.data.local.AppDatabase
import com.utku.nasarover.data.remote.NasaRoverService
import com.utku.nasarover.data.remote.RemoteDataSource
import com.utku.nasarover.util.HOST
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl(HOST)
            .client(client)
            .addConverterFactory(Json {
                prettyPrint = true
                isLenient = true
                coerceInputValues = true
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(NasaRoverService::class.java)
    }
}

val dataModule = module {
    single { RemoteDataSource(get()) }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "starDelivery")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { (get() as AppDatabase).nasaRoverDao() }
}


val repositoryModule = module {

}

val viewModelModule = module {

}

val allModules = networkModule + dataModule + repositoryModule + viewModelModule
