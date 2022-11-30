package com.baktiyar11.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.baktiyar11.numbercomposition.R
import com.baktiyar11.numbercomposition.databinding.FragmentGameFinishBinding

class GameFinishFragment : Fragment() {

    private val args by navArgs<GameFinishFragmentArgs>()
    private var _binding: FragmentGameFinishBinding? = null
    private val binding: FragmentGameFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryGame()
        bindViews()
    }

    private fun bindViews() = binding.apply {
        emojiResult.setImageResource(getSmileResId())
        args.gameResult.apply {
            tvRequiredAnswer.text = String.format(
                getString(R.string.text_present_required_answer),
                gameSettings.minCountOfRightAnswers)
            tvScoreAnswer.text = String.format(
                getString(R.string.text_score_answer), countOfRightAnswer)
            tvRequiredPercentage.text = String.format(
                getString(R.string.text_required_percentage), gameSettings.minPercentOfRightAnswers)
            tvScorePercentage.text = String.format(
                getString(R.string.text_score_percentage), getPercentOfRightAnswers())
        }
    }

    private fun getSmileResId() = if (args.gameResult.winner) R.drawable.smiling
    else R.drawable.crying

    private fun getPercentOfRightAnswers() = if (args.gameResult.countOfQuestions == 0) 0
    else ((args.gameResult.countOfRightAnswer / args.gameResult.countOfQuestions.toDouble()) * 100).toInt()

    private fun retryGame() = binding.btnRetry.setOnClickListener {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}