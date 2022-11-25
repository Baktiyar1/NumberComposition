package com.baktiyar11.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.baktiyar11.numbercomposition.databinding.FragmentChooseLevelBinding
import com.baktiyar11.numbercomposition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private val args by navArgs<ChooseLevelFragmentArgs>()
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseLevel()
    }

    private fun chooseLevel() = binding.apply {
        tvLevelTest.setOnClickListener { launchGameFragment(Level.TEST) }
        tvLevelEasy.setOnClickListener { launchGameFragment(Level.EASY) }
        tvLevelNormal.setOnClickListener { launchGameFragment(Level.NORMAL) }
        tvLevelHard.setOnClickListener { launchGameFragment(Level.HARD) }
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level, args.type))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}