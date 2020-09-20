package com.ozcanalasalvar.bitcointicker.di.module

import com.ozcanalasalvar.bitcointicker.data.remote.ApiService
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.service.ServiceDataSource
import com.ozcanalasalvar.bitcointicker.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()

    }

    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    @Named("coinApi")
    fun provideCityRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
        adapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.COIN_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }


    @Singleton
    @Provides
    fun provideForecastApiService(@Named("coinApi") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ): ServiceDataSource {
        return ServiceDataSource(
            apiService
        )
    }
}