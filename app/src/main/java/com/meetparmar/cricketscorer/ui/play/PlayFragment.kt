package com.meetparmar.cricketscorer.ui.play

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.meetparmar.cricketscorer.R
import com.meetparmar.cricketscorer.ui.activity.GameScorerActivity
import kotlinx.android.synthetic.main.fragment_play.*


class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel
    lateinit var team1: EditText
    lateinit var team2: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        playViewModel =
            ViewModelProviders.of(this).get(PlayViewModel::class.java)
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        team1 = et_team_a
        team2 = et_team_b

        team1.addTextChangedListener(textWatcher1)
        team2.addTextChangedListener(textWatcher2)

        btn_start_match.setOnClickListener {
            if (et_team_a.text.isNullOrEmpty() || et_team_b.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Team Name", Toast.LENGTH_SHORT).show()

            } else {
                val checkedRadioButtonId: Int = rg_batting.checkedRadioButtonId
                val radioButton = view?.findViewById<RadioButton>(checkedRadioButtonId)

                val intent = Intent(context, GameScorerActivity::class.java)
                intent.putExtra("teamA", et_team_a.text.toString())
                intent.putExtra("teamB", et_team_b.text.toString())
                intent.putExtra("batting", radioButton?.text.toString())
                intent.putExtra("overs", et_over.text.toString())
                startActivity(intent)
            }
        }
    }

    private var textWatcher1 = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            rb_team_a.text = p0
        }

    }

    private var textWatcher2 = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            rb_team_b.text = p0
        }
    }

}
