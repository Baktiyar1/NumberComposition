package com.baktiyar11.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baktiyar11.numbercomposition.databinding.FragmentChooseTypeBinding
import com.baktiyar11.numbercomposition.domain.entity.Type

class ChooseTypeFragment : Fragment() {

    private var _binding: FragmentChooseTypeBinding? = null
    private val binding: FragmentChooseTypeBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseTypeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChooseTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseType()
    }

    private fun chooseType() = binding.apply {
        btnTypePlus.setOnClickListener { launchChooseLevelFragment(Type.PLUS) }
        btnTypeMinus.setOnClickListener { launchChooseLevelFragment(Type.MINUS) }
        btnTypeMultiplication.setOnClickListener { launchChooseLevelFragment(Type.MULTI) }
        btnTypeDivision.setOnClickListener { launchChooseLevelFragment(Type.DIVISION) }
    }

    private fun launchChooseLevelFragment(type: Type) {
        findNavController().navigate(
            ChooseTypeFragmentDirections.actionChooseTypeFragmentToChooseLevelFragment(type))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}