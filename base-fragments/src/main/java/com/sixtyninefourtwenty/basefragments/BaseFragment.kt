package com.sixtyninefourtwenty.basefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Suppress("unused")
@Deprecated(
    message = "Use ViewBindingFragment instead.",
    replaceWith = ReplaceWith(
        expression = "ViewBindingFragment",
        imports = ["com.sixtyninefourtwenty.basefragments.ViewBindingFragment"]
    )
)
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

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
