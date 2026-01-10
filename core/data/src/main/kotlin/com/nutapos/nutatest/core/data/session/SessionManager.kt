package com.nutapos.nutatest.core.data.session

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session_prefs")

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

  private object PreferencesKeys {
    val LOGGED_IN_USER_ID = longPreferencesKey("logged_in_user_id")
  }

  val loggedInUserIdFlow: Flow<Long?> = context.dataStore.data.map {
    it[PreferencesKeys.LOGGED_IN_USER_ID]
  }

  suspend fun setLoggedInUserId(userId: Long?) {
    context.dataStore.edit {
      if (userId == null) {
        it.remove(PreferencesKeys.LOGGED_IN_USER_ID)
      } else {
        it[PreferencesKeys.LOGGED_IN_USER_ID] = userId
      }
    }
  }
}
