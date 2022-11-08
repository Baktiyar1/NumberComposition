package com.baktiyar11.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baktiyar11.numbercomposition.R
import com.baktiyar11.numbercomposition.databinding.FragmentGameBinding
import com.baktiyar11.numbercomposition.domain.entity.GameResult
import com.baktiyar11.numbercomposition.domain.entity.GameSettings
import com.baktiyar11.numbercomposition.domain.entity.Level

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")
    private lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvOption1.setOnClickListener {
            launchGameFinishFragment(GameResult(winner = true, countOfRightAnswer = 1,
                countOfQuestions = 1, GameSettings(maxSumValue = 1, minCountOfRightAnswers = 1,
                    minPercentOfRightAnswers = 1, gameTimeInSecond = 1)))
        }
    }

    private fun launchGameFinishFragment(gameResult: GameResult) = requireActivity()
        .supportFragmentManager.beginTransaction()
        .replace(R.id.main_container, GameFinishFragment.newInstance(gameResult = gameResult))
        .addToBackStack(null).commit()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() = requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
        level = it
    }

    companion object {
        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment = GameFragment().apply {
            arguments = Bundle().apply { putParcelable(KEY_LEVEL, level) }
        }
    }
}