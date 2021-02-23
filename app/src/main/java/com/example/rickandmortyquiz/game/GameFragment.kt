package com.example.rickandmortyquiz

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.rickandmortyquiz.databinding.FragmentGameBinding
import com.example.rickandmortyquiz.game.GameViewModel
import com.example.rickandmortyquiz.game.Question


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate (
            inflater, R.layout.fragment_game, container,false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { newQuestion ->
            binding.questionText.text = newQuestion.toString()
            setRadioButtons()
            setPicture()
        })

        viewModel.scoreString.observe(viewLifecycleOwner, Observer {newScore ->
            binding.textCurrentScore.text = newScore.toString()
        })

        viewModel.answerChecked.observe(viewLifecycleOwner, Observer {
            setRadioButtons()
            setPicture()
            checkFinishGame()
        })

        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun setRadioButtons() {
        var question = viewModel.getQuestionBank().get(viewModel.getCurrentQuestionIndex())

        if (question.attempted) {
            disableRadioButtons()
            selectRadioButton(question.answered)
        }
        else {
            enableRadioButtons()
            deselectRadioButtons()
        }
    }

    private fun disableRadioButtons() {
        binding.radioButtonTrue.setEnabled(false)
        binding.radioButtonFalse.setEnabled(false)
    }

    private fun enableRadioButtons() {
        binding.radioButtonTrue.setEnabled(true)
        binding.radioButtonFalse.setEnabled(true)
    }

    private fun deselectRadioButtons() {
        binding.radioButtonTrue.isChecked = false
        binding.radioButtonFalse.isChecked = false
    }

    private fun selectRadioButton(chosen: Boolean) {
        binding.radioButtonTrue.isChecked = chosen
        binding.radioButtonFalse.isChecked = !chosen
    }

    private fun setPicture() {
        var question = viewModel.getQuestionBank().get(viewModel.getCurrentQuestionIndex())

        if (question.attempted && question.answer == question.answered) {
            binding.imageRight.setVisibility(View.VISIBLE)
            binding.imageWrong.setVisibility(View.INVISIBLE)
        }
        else if (question.attempted) {
            binding.imageRight.setVisibility(View.INVISIBLE)
            binding.imageWrong.setVisibility(View.VISIBLE)
        }
        else {
            binding.imageRight.setVisibility(View.INVISIBLE)
            binding.imageWrong.setVisibility(View.INVISIBLE)
        }
    }

    private fun checkFinishGame() {
        if (viewModel.getGameOver())
            view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.scoreString.value.toString()))
    }
}