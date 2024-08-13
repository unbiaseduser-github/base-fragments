package com.sixtyninefourtwenty.basefragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class ViewBottomSheetDialogFragment<V : View>(
    private val viewCreation: (Context) -> V
) : BottomSheetDialogFragment() {

    private var _view: V? = null

    fun requireTypedView() = _view ?: error("View accessed before onCreateView or after onDestroyView")

    open fun onCreateView(view: V, inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = Unit

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _view = viewCreation(requireContext())
        onCreateView(requireTypedView(), inflater, container, savedInstanceState)
        return requireTypedView()
    }

    open fun onDestroyView(view: V) = Unit

    final override fun onDestroyView() {
        onDestroyView(requireTypedView())
        _view = null
        super.onDestroyView()
    }

}