package com.softvision.alexlepadatu.typicode.data.api

import com.softvision.alexlepadatu.typicode.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitAlbumsProvider {

    @Singleton
    @Provides
    fun provideAlbumsApi(retrofit: Retrofit): AlbumsApi = retrofit.create(AlbumsApi::class.java)

    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .client(httpClient)
            .build()
    }

    @Provides
    fun provideConverterFactory(serializer: Moshi): Converter.Factory = MoshiConverterFactory.create(serializer)

    @Provides
    fun provideSerializer(): Moshi = Moshi.Builder()
        .build()

    @Provides
    fun provideHttpClient(): OkHttpClient = provideHttpClientBuilder().build()

    @Provides
    fun provideHttpClientBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)

        interceptors.forEach {
            httpClientBuilder.addInterceptor(it)
        }

        return httpClientBuilder
    }

    private val interceptors: List<Interceptor> by lazy {
        val list = mutableListOf<Interceptor>()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            list.add(loggingInterceptor)
        }

        return@lazy list
    }
}
