package com.upstars.masterpokiescasino.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.upstars.masterpokiescasino.di.DispatcherIO
import com.upstars.masterpokiescasino.screens.main.domain.Prizes
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
class PrizesRepository @Inject constructor(
    private val context: Context,
    @DispatcherIO private val dispatcher: CoroutineDispatcher
) {
    private val Context.dataStore by preferencesDataStore(name = "prizes")

    private val coins = intPreferencesKey("coins")
    private val goldBars = intPreferencesKey("gold_bars")

    fun getPrizesFlow(): Flow<Prizes> = context.dataStore
        .data
        .map { Prizes(coins = it[coins] ?: 0, goldBars = it[goldBars] ?: 0) }
        .distinctUntilChanged()
        .flowOn(dispatcher)

    suspend fun addCoins(count: Int) = withContext(dispatcher) {
        context.dataStore.edit {
            it[coins] = (it[coins] ?: 0) + count
        }
    }

    suspend fun addGoldBars(count: Int) = withContext(dispatcher) {
        context.dataStore.edit {
            it[goldBars] = (it[goldBars] ?: 0) + count
        }
    }
}
