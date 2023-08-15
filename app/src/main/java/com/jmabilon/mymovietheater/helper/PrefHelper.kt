package com.jmabilon.mymovietheater.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jmabilon.mymovietheater.extension.getBooleanWithTrueByDefault
import com.jmabilon.mymovietheater.extension.setBoolean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PrefHelper(context: Context) {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val dataStore = context.datastore

    companion object {
        private val ON_BOARDING_VISIBILITY = booleanPreferencesKey("ON_BOARDING_VISIBILITY")
    }

    suspend fun setOnBoardingVisibility(value: Boolean) {
        dataStore.setBoolean(ON_BOARDING_VISIBILITY, value)
    }

    fun getOnBoardingVisibility(): Flow<Boolean> {
        return dataStore.getBooleanWithTrueByDefault(ON_BOARDING_VISIBILITY)
    }
}