package edu.skku.cs.translatorclone.global

import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {

                    viewModel {
                        TranslatorViewModel()
                    }
                }
            )

        }
    }
}