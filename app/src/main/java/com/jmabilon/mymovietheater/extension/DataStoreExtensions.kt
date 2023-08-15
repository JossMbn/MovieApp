package com.jmabilon.mymovietheater.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map

suspend fun DataStore<Preferences>.setBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
    this.edit { pref ->
        pref[key] = value
    }
}

fun DataStore<Preferences>.getBooleanWithTrueByDefault(key: Preferences.Key<Boolean>): Flow<Boolean> {
    return this.data.map { prefs ->
        prefs[key] ?: true
    }
}