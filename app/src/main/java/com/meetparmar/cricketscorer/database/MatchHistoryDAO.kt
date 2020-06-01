package com.meetparmar.cricketscorer.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MatchHistoryDAO {

    @Insert
    fun insert(history: MatchHistory)

    @Update
    fun update(history: MatchHistory)

    @Query("SELECT * from cricket_match_history_table WHERE id = :key")
    fun get(key: Long): MatchHistory?

    @Query("DELETE FROM cricket_match_history_table")
    fun clear()

    @Query("SELECT * FROM cricket_match_history_table ORDER BY id DESC")
    fun getAllMatches(): LiveData<List<MatchHistory>>

    @Query("SELECT * FROM cricket_match_history_table ORDER BY id DESC LIMIT 1")
    fun getLatestMatch(): MatchHistory?
}
