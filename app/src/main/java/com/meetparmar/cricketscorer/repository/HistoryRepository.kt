package com.meetparmar.cricketscorer.repository

import androidx.lifecycle.LiveData
import com.meetparmar.cricketscorer.database.HistoryDatabase
import com.meetparmar.cricketscorer.database.MatchHistory

class HistoryRepository(val database: HistoryDatabase) {

    fun insertMatchHistory(history: MatchHistory) {
        database.matchHistoryDAO.insert(history)
    }

    fun getMatchHistory(): LiveData<List<MatchHistory>> {
        return database.matchHistoryDAO.getAllMatches()
    }
}