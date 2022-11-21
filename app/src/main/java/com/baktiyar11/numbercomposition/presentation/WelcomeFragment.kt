package com.baktiyar11.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baktiyar11.numbercomposition.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action()
    }

    private fun action() = binding.btnUnderstand.setOnClickListener {
        launchChooseLevelFragment()
    }

    private fun launchChooseLevelFragment() = findNavController()
        .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToChooseLevelFragment())

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        layoutInflater
    }
}