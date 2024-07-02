package com.example.moviescompose.di

import com.example.moviescompose.data.repository.MovieRepository
import com.example.moviescompose.data.repository.MovieRepositoryImpl
import com.example.moviescompose.data.service.MoviesApi
import com.example.moviescompose.utils.BASE_URL
import com.example.moviescompose.utils.CERTIFICATE_AUTHORITY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(handshakeCertificates: HandshakeCertificates): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .sslSocketFactory(
                handshakeCertificates.sslSocketFactory(),
                handshakeCertificates.trustManager
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideCertificates() =
        HandshakeCertificates.Builder()
            .addTrustedCertificate(CERTIFICATE_AUTHORITY.trimIndent().decodeCertificatePem())
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideMoviesRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository =
        movieRepositoryImpl
}