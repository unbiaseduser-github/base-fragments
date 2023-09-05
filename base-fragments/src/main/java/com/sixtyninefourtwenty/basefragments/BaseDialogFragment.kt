package com.sixtyninefourtwenty.basefragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : AppCompatDialogFragment() {

    private var _binding: VB? = null
    private val binding: VB get() = _binding!!

    protected abstract fun initBinding(): VB
    protected abstract fun onCreateDialog(binding: VB, savedInstanceState: Bundle?): Dialog
    protected open fun onDestroyView(binding: VB) = Unit

    final override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = initBinding()
        return onCreateDialog(binding, savedInstanceState)
    }

    final override fun onDestroyView() {
        super.onDestroyView()
        onDestroyView(binding)
        _binding = null
    }

}