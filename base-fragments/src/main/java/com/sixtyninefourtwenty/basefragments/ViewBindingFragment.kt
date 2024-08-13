package com.sixtyninefourtwenty.basefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class ViewBindingFragment<VB : ViewBinding>(
    private val bindingCreation: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null

    fun requireBinding() = _binding ?: error("Binding accessed before onCreateView or after onDestroyView")

    open fun onCreateView(binding: VB, inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = Unit

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindingCreation(inflater, container, false)
        onCreateView(requireBinding(), inflater, container, savedInstanceState)
        return requireBinding().root
    }

    open fun onDestroyView(binding: VB) = Unit

    final override fun onDestroyView() {
        onDestroyView(requireBinding())
        _binding = null
        super.onDestroyView()
    }

}