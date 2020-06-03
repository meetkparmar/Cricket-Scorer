package com.meetparmar.cricketscorer.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MatchHistoryDAO {

    @Insert
    fun insert(history: MatchHistory)

    @Query("SELECT * FROM cricket_match_history_table ORDER BY id DESC")
    fun getAllMatches(): LiveData<List<MatchHistory>>

}
