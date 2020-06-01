package com.meetparmar.cricketscorer.ui.play

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.meetparmar.cricketscorer.R
import kotlinx.android.synthetic.main.fragment_play.*


class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel
    lateinit var team1: EditText
    lateinit var team2: EditText
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton


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


        val teamA = et_team_a.text.toString()
        val teamB = et_team_b.text.toString()
        val overs = et_over.text.toString()
        team1 = et_team_a
        team2 = et_team_b

        team1.addTextChangedListener(textWatcher1)
        team2.addTextChangedListener(textWatcher2)


        btn_start_match.setOnClickListener {
            if (teamA.isNullOrEmpty() || teamB.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Team Name", Toast.LENGTH_SHORT).show()
            }
//            radioGroup = rg_batting
//            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
//            Toast.makeText(context, intSelectButton, Toast.LENGTH_SHORT).show()

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
