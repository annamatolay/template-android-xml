package dev.anmatolay.template.xml.core.network

import com.squareup.moshi.Moshi
import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.core.network.interceptor.interceptorList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


object ApiClientFactory {

    private val moshi by inject<Moshi>(Moshi::class.java)

    private const val timeOutInSeconds = 60L

    private val logger =
        HttpLoggingInterceptor.Logger { message -> Timber.tag("Retrofit - OkHttp").d(message) }
    private val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

    )
    private val okHttpClientBuilder =
        OkHttpClient().newBuilder()
            .connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

    fun createRetrofit(): Retrofit.Builder {
        interceptorList.forEach { okHttpClientBuilder.addInterceptor(it) }

        return Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }


}
