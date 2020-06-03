package com.meetparmar.cricketscorer.repository

import android.content.Context
import com.meetparmar.cricketscorer.database.HistoryDatabase
import com.meetparmar.cricketscorer.database.MatchHistory

class LocalRepository(context: Context) {

    private val database = HistoryDatabase.getInstance(context.applicationContext)

    fun insertMatchHistory(history: MatchHistory){
        database.matchHistoryDAO.insert(history)
    }

    fun getMatchHistory(){
        database.matchHistoryDAO.getAllMatches()
    }
}