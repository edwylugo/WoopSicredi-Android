package edwylugo.woopsicredi.core.app

import android.app.Application
import edwylugo.woopsicredi.core.appModule
import org.koin.android.ext.android.startKoin

class WoopSicredi: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}