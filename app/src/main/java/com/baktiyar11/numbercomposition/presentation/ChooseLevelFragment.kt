package com.baktiyar11.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baktiyar11.numbercomposition.R
import com.baktiyar11.numbercomposition.databinding.FragmentChooseLevelBinding
import com.baktiyar11.numbercomposition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseLevel()
    }

    private fun chooseLevel() = binding.apply {
        btnLevelTest.setOnClickListener { launchGameFragment(Level.TEST) }
        btnLevelEasy.setOnClickListener { launchGameFragment(Level.EASY) }
        btnLevelNormal.setOnClickListener { launchGameFragment(Level.NORMAL) }
        btnLevelHard.setOnClickListener { launchGameFragment(Level.HARD) }
    }

    private fun launchGameFragment(level: Level) = requireActivity()
        .supportFragmentManager.beginTransaction()
        .replace(R.id.main_container, GameFragment.newInstance(level = level))
        .addToBackStack(GameFragment.NAME).commit()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ChooseLevelFragment = ChooseLevelFragment()
    }
}