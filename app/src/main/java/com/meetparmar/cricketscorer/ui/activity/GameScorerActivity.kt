package com.meetparmar.cricketscorer.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.meetparmar.cricketscorer.R
import com.meetparmar.cricketscorer.database.MatchHistory
import kotlinx.android.synthetic.main.activity_game_scorer.*
import kotlinx.android.synthetic.main.result_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GameScorerActivity : AppCompatActivity() {
    lateinit var teamA: String
    lateinit var teamB: String
    lateinit var batting: String
    lateinit var tOvers: String
    var totalOvers: Int = 0
    var teamAScore: Int = 0
    var teamBScore: Int = 0
    var teamAWicket: Int = 0
    var teamAOver: Int = 0
    var teamBWicket: Int = 0
    var teamBOver: Int = 0
    var teamBBall: Int = 0
    var teamABall: Int = 0
    var inning: Int = 0
    var balls: Int = 0
    var thisOver = ArrayList<String>()
    private var needRun: Int = 0
    private var leftBalls: Int = 0
    var date: String? = null
    var teamWon: String? = null
    var matchHistory: MatchHistory? = null
    lateinit var viewModel: GameScorerViewModel
    var check : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GameScorerViewModel::class.java)
        setContentView(R.layout.activity_game_scorer)
        teamA = intent.getStringExtra("teamA")!!
        teamB = intent.getStringExtra("teamB")!!
        batting = intent.getStringExtra("batting")!!
        tOvers = intent.getStringExtra("overs")!!
        tv_batting_team.text = batting
        totalOvers = if (tOvers == "") {
            10
        } else {
            tOvers.toInt()
        }

        btn_quit.setOnClickListener {
            finish()
        }

        btn_undo.setOnClickListener {
            if (thisOver.size != 0) {
                undoLastMove()
            }
        }

        btn_dot.setOnClickListener {
            thisOver.add("0")
            addToScore(0, 0, 1)
        }

        btn_one.setOnClickListener {
            thisOver.add("1")
            addToScore(1, 0, 1)
        }

        btn_two.setOnClickListener {
            thisOver.add("2")
            addToScore(2, 0, 1)
        }

        btn_three.setOnClickListener {
            thisOver.add("3")
            addToScore(3, 0, 1)
        }

        btn_four.setOnClickListener {
            thisOver.add("4")
            addToScore(4, 0, 1)
        }

        btn_six.setOnClickListener {
            thisOver.add("6")
            addToScore(6, 0, 1)
        }

        btn_wide.setOnClickListener {
            thisOver.add("WD")
            addToScore(1, 0, 0)
        }

        btn_no_ball.setOnClickListener {
            thisOver.add("NB")
            addToScore(0, 0, 0)
        }

        btn_out.setOnClickListener {
            thisOver.add("W")
            addToScore(0, 1, 1)
        }

    }

    private fun undoLastMove() {
        if (batting == teamA) {
            when {
                thisOver[thisOver.size - 1] == "WD" -> {
                    teamAScore -= 1
                    teamABall -= 0
                    setToWonView(-1, 0)
                }
                thisOver[thisOver.size - 1] == "NB" -> {
                    teamAScore -= 0
                    teamABall -= 0
                    setToWonView(0, 0)
                }
                thisOver[thisOver.size - 1] == "W" -> {
                    teamAScore -= 0
                    teamABall -= 1
                    teamAWicket -= 1
                    balls -= 1
                    setToWonView(0, -1)
                }
                else -> {
                    teamAScore -= thisOver[thisOver.size - 1].toInt()
                    teamABall -= 1
                    balls -= 1
                    setToWonView(-(thisOver[thisOver.size - 1].toInt()), -1)
                }
            }
            tv_batting_score.text = "$teamAScore - $teamAWicket"
            tv_bowling_score.text = "( $teamAOver.$teamABall )"
        } else if (batting == teamB) {
            when {
                thisOver[thisOver.size - 1] == "WD" -> {
                    teamBScore -= 1
                    teamBBall -= 0
                    setToWonView(-1, 0)
                }
                thisOver[thisOver.size - 1] == "NB" -> {
                    teamBScore -= 0
                    teamBBall -= 0
                    setToWonView(0, 0)
                }
                thisOver[thisOver.size - 1] == "W" -> {
                    teamBScore -= 0
                    teamBBall -= 1
                    teamBWicket -= 1
                    balls -= 1
                    setToWonView(0, -1)
                }
                else -> {
                    teamBScore -= thisOver[thisOver.size - 1].toInt()
                    teamBBall -= 1
                    balls -= 1
                    setToWonView(-(thisOver[thisOver.size - 1].toInt()), -1)
                }
            }
            tv_batting_score.text = "$teamBScore - $teamBWicket"
            tv_bowling_score.text = "( $teamBOver.$teamBBall )"
        }
        thisOver.removeAt(thisOver.size - 1)
        displayThisOver()
    }

    private fun addToScore(run: Int, wicket: Int, ball: Int) {
        if (batting == teamA) {
            teamAScore += run
            teamAWicket += wicket
            balls += ball
            teamAOver = balls / 6
            teamABall = balls % 6
            tv_batting_score.text = "$teamAScore - $teamAWicket"
            tv_bowling_score.text = "( $teamAOver.$teamABall )"
            displayThisOver()
            if (inning == 1) {
                setToWonView(run, ball)
                if (teamAScore > teamBScore) {
                    openDialogBox()
                    check = true
                }
            }
            if (isFinish(teamA)) {
                if (inning == 1) {
                    if (!check) {
                        openDialogBox()
                        check = false
                    }
                }
                changeInning(teamA)
            }
        } else if (batting == teamB) {
            teamBScore += run
            teamBWicket += wicket
            balls += ball
            teamBOver = balls / 6
            teamBBall = balls % 6
            tv_batting_score.text = "$teamBScore - $teamBWicket"
            tv_bowling_score.text = "( $teamBOver.$teamBBall )"
            displayThisOver()
            if (inning == 1) {
                setToWonView(run, ball)
                if (teamBScore > teamAScore) {
                    openDialogBox()
                    check = true
                }
            }
            if (isFinish(teamB)) {
                if (inning == 1) {
                    if (!check) {
                        openDialogBox()
                        check = false
                    }
                }
                changeInning(teamB)
            }
        }
    }

    private fun setToWonView(run: Int, ball: Int) {
        needRun -= run
        leftBalls -= ball
        tv_to_win.text = "$batting need $needRun in $leftBalls balls"
    }

    private fun openDialogBox() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.result_dialog, viewGroup, false)
        builder.setView(dialogView)
        val alertDialog: AlertDialog = builder.create()
        when {
            teamAScore > teamBScore -> {
                dialogView.result.text = "$teamA Won the Match"
                teamWon = teamA
            }
            teamAScore == teamBScore -> {
                dialogView.resultTrophy.setImageResource(R.drawable.ic_trophy_tie)
                dialogView.result.text = "That was a tie!"
                teamWon = "Tie"
            }
            else -> {
                dialogView.result.text = "$teamB Won the Match"
                teamWon = teamB
            }
        }
        alertDialog.setCancelable(false)
        alertDialog.show()
        dialogView.btn_quit.setOnClickListener {
            alertDialog.dismiss()
            finish()
        }
        setDate()
        setValuesForMatchHistory()
    }

    private fun setValuesForMatchHistory() {
        val matchHistory = MatchHistory(
            0,
            date!!,
            teamA,
            teamB,
            teamAScore,
            teamBScore,
            teamAWicket,
            teamBWicket,
            "$teamAOver.$teamABall",
            "$teamBOver.$teamBBall",
            teamWon!!
        )
        viewModel.insertMatchHistory(matchHistory)
    }

    private fun setDate() {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val calander = Calendar.getInstance()
        date = sdf.format(calander.time)
    }

    private fun changeInning(battingTeam: String) {
        inning = 1
        cd_team_a_score.visibility = View.VISIBLE
        cd_to_win_board.visibility = View.VISIBLE
        tv_team_a.text = battingTeam
        if (battingTeam == teamA) {
            batting = teamB
            tv_batting_team.text = teamB
            tv_team_a_score.text = "$teamAScore - $teamAWicket"
            tv_team_a_overs.text = "( $teamAOver.$teamABall )"
            tv_batting_score.text = "$teamBScore - $teamBWicket"
            tv_bowling_score.text = "( $teamBOver.$teamBBall )"
            balls = 0
            needRun = teamAScore + 1
            leftBalls = totalOvers * 6
            tv_to_win.text = "$batting need $needRun in $leftBalls balls"
            displayThisOver()
        } else {
            batting = teamA
            tv_batting_team.text = teamA
            tv_team_a_score.text = "$teamBScore - $teamBWicket"
            tv_team_a_overs.text = "( $teamBOver.$teamBBall )"
            tv_batting_score.text = "$teamAScore - $teamAWicket"
            tv_bowling_score.text = "( $teamAOver.$teamABall )"
            balls = 0
            needRun = teamBScore + 1
            leftBalls = totalOvers * 6
            tv_to_win.text = "$batting need $needRun in $leftBalls balls"
            displayThisOver()
        }
    }

    private fun displayThisOver() {
        val builder = StringBuilder()
        for (details in thisOver) {
            builder.append("$details ")
        }
        tv_this_over_score.text = builder.toString()
        if (balls % 6 == 0) {
            thisOver.removeAll(thisOver)
        }
    }

    private fun isFinish(team: String): Boolean {
        if (team == teamA) {
            if ((balls == totalOvers * 6) || teamAWicket == 10) {
                return true
            }
        } else {
            if ((balls == totalOvers * 6) || teamBWicket == 10) {
                return true
            }
        }
        return false
    }
}