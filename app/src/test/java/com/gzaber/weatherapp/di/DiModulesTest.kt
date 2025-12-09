package com.gzaber.weatherapp.di

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.verify.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(KoinExperimentalAPI::class)
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DiModulesTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(ApplicationProvider.getApplicationContext())
        modules(
            dataModule,
            viewModelModule
        )
    }

    @Test
    fun verifyKoinGraph() {
        val allModules = module {
            includes(dataModule, viewModelModule)
        }
        allModules.verify(
            extraTypes = listOf(
                Context::class
            )
        )
    }
}
