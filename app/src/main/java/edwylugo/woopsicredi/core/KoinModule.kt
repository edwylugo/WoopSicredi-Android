package edwylugo.woopsicredi.core

import android.view.View
import java.util.concurrent.TimeUnit
import edwylugo.woopsicredi.BuildConfig
import edwylugo.woopsicredi.viewmodel.EventViewModel
import edwylugo.woopsicredi.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { createOkHttpClient() }
    single { createWoopSicrediService(get()) }

    viewModel { (root: View) -> EventViewModel(root, get()) }
    viewModel { (root: View) -> MainViewModel(root, get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun createWoopSicrediService(client: OkHttpClient): WoopSicrediService {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WoopSicrediService::class.java)
}