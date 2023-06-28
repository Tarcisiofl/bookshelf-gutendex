package br.com.tarcisiofl.bookshelf

import android.app.Application
import br.com.tarcisiofl.bookshelf.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            loadKoinModules(appModule)
        }
    }
}