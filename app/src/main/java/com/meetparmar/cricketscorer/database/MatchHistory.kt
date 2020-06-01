package com.meetparmar.cricketscorer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cricket_match_history_table")
data class MatchHistory(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "date")
    val date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "team_a_name")
    var team_a_name: String,

    @ColumnInfo(name = "team_b_name")
    var team_b_name: String,

    @ColumnInfo(name = "team_a_score")
    var team_a_score: Int = 0,

    @ColumnInfo(name = "team_b_score")
    var team_b_score: Int = 0,

    @ColumnInfo(name = "team_a_wicket")
    var team_a_wicket: Int = 0,

    @ColumnInfo(name = "team_b_wicket")
    var team_b_wicket: Int = 0,

    @ColumnInfo(name = "team_a_overs")
    var team_a_overs: Double = 0.0,

    @ColumnInfo(name = "team_b_overs")
    var team_b_overs: Double = 0.0
)
