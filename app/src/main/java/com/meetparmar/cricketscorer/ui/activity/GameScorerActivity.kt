package com.meetparmar.cricketscorer.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meetparmar.cricketscorer.R

class GameScorerActivity : AppCompatActivity() {
    lateinit var teamA : String
    lateinit var teamB : String
    lateinit var batting : String
    var overs : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_scorer)
        teamA = intent.getStringExtra("teamA")!!
        teamB = intent.getStringExtra("teamB")!!
        batting = intent.getStringExtra("batting")!!
        overs = intent.getStringExtra("overs").toInt()
    }
}