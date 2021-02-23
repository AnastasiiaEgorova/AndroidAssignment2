package com.example.rickandmortyquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyquiz.databinding.FragmentGameBinding
import com.example.rickandmortyquiz.game.GameViewModel


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate (
            inflater, R.layout.fragment_game, container,false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.questionText.text = newQuestion.toString()
        })

        viewModel.scoreString.observe(viewLifecycleOwner, Observer {newScore ->
            binding.textCurrentScore.text = newScore.toString()
        })

        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}