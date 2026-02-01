package com.gzaber.weatherapp.di

import com.gzaber.weatherapp.util.KoinTestRule
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(KoinExperimentalAPI::class)
@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class DiModulesTest : KoinTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val koinTestRule = KoinTestRule(modules = listOf(dataModule, viewModelModule))

    @Test
    fun verifyKoinGraph() {
        module {
            includes(dataModule, viewModelModule)
        }.verify()
    }
}
