package com.example.rickandmortyquiz.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.rickandmortyquiz.R
import com.example.rickandmortyquiz.databinding.FragmentGameBinding
import com.example.rickandmortyquiz.databinding.FragmentScoreBinding
import com.example.rickandmortyquiz.game.GameViewModel

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate (
            inflater, R.layout.fragment_score, container,false)

        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        binding.textScore.text = args.score.toString()

        return binding.root
    }

}