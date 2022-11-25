package com.baktiyar11.numbercomposition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.baktiyar11.numbercomposition.R
import com.baktiyar11.numbercomposition.databinding.FragmentGameBinding
import com.baktiyar11.numbercomposition.domain.entity.GameResult

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()
    private val viewModel by lazy {
        ViewModelProvider(this)[GameViewModel::class.java]
    }
    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }
    private val cvOptions by lazy {
        mutableListOf<CardView>().apply {
            add(binding.cvOption1)
            add(binding.cvOption2)
            add(binding.cvOption3)
            add(binding.cvOption4)
            add(binding.cvOption5)
            add(binding.cvOption6)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions() {
        for (i in 0 until tvOptions.size) cvOptions[i].setOnClickListener {
            viewModel.chooseAnswer(tvOptions[i].text.toString().toInt())
        }
    }

    private fun observeViewModel() = binding.apply {
        viewModel.apply {
            question.observe(viewLifecycleOwner) {
                tvSum.text = it.sum.toString()
                tvLeftNumber.text = it.visibleNumber.toString()
                for (i in 0 until tvOptions.size) tvOptions[i].text = it.options[i].toString()
            }
            percentOfRightAnswer.observe(viewLifecycleOwner) {
                progressBar.setProgress(it, true)
            }
            enoughCount.observe(viewLifecycleOwner) {
                tvAnswersProgress.setTextColor(getColorByState(it))
            }
            enoughPercent.observe(viewLifecycleOwner) {
                progressBar.progressTintList = ColorStateList.valueOf(getColorByState(it))
            }
            formattedTime.observe(viewLifecycleOwner) { tvTimer.text = it }
            minPercent.observe(viewLifecycleOwner) { progressBar.secondaryProgress = it }
            gameResult.observe(viewLifecycleOwner) { launchGameFinishedFragment(it) }
            progressAnswer.observe(viewLifecycleOwner) {
                tvAnswersProgress.text = String.format(requireContext().resources.getString(
                    R.string.text_explanation_of_what_percentage_of_correct_answers), it[0], it[1])
            }
            startGame(level = args.level, type = args.type)
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) android.R.color.holo_green_light
        else android.R.color.holo_red_light
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishFragment(gameResult))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}