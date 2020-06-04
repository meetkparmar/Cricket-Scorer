package com.meetparmar.cricketscorer.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.meetparmar.cricketscorer.database.HistoryDatabase
import com.meetparmar.cricketscorer.database.MatchHistory
import com.meetparmar.cricketscorer.repository.HistoryRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class GameScorerViewModel(context: Application) : AndroidViewModel(context) {

    private var parentJob = SupervisorJob()
    var repository = HistoryRepository(HistoryDatabase.getInstance(context))

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun insertMatchHistory(matchHistory: MatchHistory) {
        scope.launch {
            withContext(Dispatchers.IO) {
                repository.insertMatchHistory(matchHistory)
            }
        }
    }


}