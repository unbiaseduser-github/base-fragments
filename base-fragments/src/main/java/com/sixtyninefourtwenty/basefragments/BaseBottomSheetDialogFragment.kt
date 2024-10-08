package com.sixtyninefourtwenty.basefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("unused")
@Deprecated(
    message = "Use ViewBindingBottomSheetDialogFragment instead.",
    replaceWith = ReplaceWith(
        expression = "ViewBindingBottomSheetDialogFragment",
        imports = ["com.sixtyninefourtwenty.basefragments.ViewBindingBottomSheetDialogFragment"]
    )
)
abstract class BaseBottomSheetDialogFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    private val binding: VB get() = _binding!!

    protected abstract fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun onViewCreated(binding: VB, savedInstanceState: Bundle?)
    protected open fun onDestroyView(binding: VB) = Unit

    fun requireBinding() = _binding ?: error("Binding object accessed before onCreateView or after onDestroyView")

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = onCreateView(inflater, container)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated(binding, savedInstanceState)
    }

    final override fun onDestroyView() {
        super.onDestroyView()
        onDestroyView(binding)
        _binding = null
    }

}