package com.sixtyninefourtwenty.basefragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class ViewBindingDialogFragment<VB : ViewBinding>(
    private val bindingCreation: (LayoutInflater) -> VB
) : DialogFragment() {

    private var _binding: VB? = null

    fun requireBinding() = _binding ?: error("Binding accessed before onCreateDialog or after onDestroyView")

    abstract fun onCreateDialog(binding: VB, savedInstanceState: Bundle?): Dialog

    final override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = bindingCreation(layoutInflater)
        return onCreateDialog(requireBinding(), savedInstanceState)
    }

    open fun onDestroyView(binding: VB) = Unit

    final override fun onDestroyView() {
        onDestroyView(requireBinding())
        _binding = null
        super.onDestroyView()
    }

}