package com.gzaber.weatherapp.data.repository.userpreferences

import androidx.datastore.core.CorruptionException
import com.gzaber.weatherapp.UserPreferences
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class UserPreferencesSerializerTest {

    @Test
    fun defaultValue_returnsDefaultInstance() {
        assertEquals(
            UserPreferences.getDefaultInstance(),
            UserPreferencesSerializer.defaultValue
        )
    }

    @Test
    fun writeTo_writesUserPreferencesToOutputStream() = runTest {
        val userPreferences = UserPreferences.newBuilder()
            .setName("Warsaw")
            .setCountry("Poland")
            .build()
        val outputStream = ByteArrayOutputStream()

        UserPreferencesSerializer.writeTo(userPreferences, outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())
        val readUserPreferences = UserPreferences.parseFrom(inputStream)
        assertEquals(userPreferences, readUserPreferences)
    }

    @Test
    fun readFrom_readsUserPreferencesFromInputStream() = runTest {
        val userPreferences = UserPreferences.newBuilder()
            .setName("Warsaw")
            .setCountry("Poland")
            .build()
        val outputStream = ByteArrayOutputStream()
        userPreferences.writeTo(outputStream)
        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        val readUserPreferences = UserPreferencesSerializer.readFrom(inputStream)

        assertEquals(userPreferences, readUserPreferences)
    }

    @Test(expected = CorruptionException::class)
    fun readFrom_throwsCorruptionException_whenInputStreamIsInvalid() = runTest {
        val inputStream = ByteArrayInputStream(byteArrayOf(0, 1, 2, 3))

        UserPreferencesSerializer.readFrom(inputStream)
    }
}
