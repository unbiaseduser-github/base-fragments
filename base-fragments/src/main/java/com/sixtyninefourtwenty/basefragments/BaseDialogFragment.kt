package com.sixtyninefourtwenty.basefragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding

@Suppress("unused")
@Deprecated(
    message = "Use ViewBindingDialogFragment instead.",
    replaceWith = ReplaceWith(
        expression = "ViewBindingDialogFragment",
        imports = ["com.sixtyninefourtwenty.basefragments.ViewBindingDialogFragment"]
    )
)
abstract class BaseDialogFragment<VB : ViewBinding> : AppCompatDialogFragment() {

    private var _binding: VB? = null
    private val binding: VB get() = _binding!!

    protected abstract fun initBinding(): VB
    protected abstract fun onCreateDialog(binding: VB, savedInstanceState: Bundle?): Dialog
    protected open fun onDestroyView(binding: VB) = Unit

    fun requireBinding() = _binding ?: error("Binding object accessed before onCreateDialog or after onDestroyView")

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