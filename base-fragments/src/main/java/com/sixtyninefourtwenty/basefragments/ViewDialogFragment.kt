package com.sixtyninefourtwenty.basefragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

@Suppress("unused", "MemberVisibilityCanBePrivate")
abstract class ViewDialogFragment<V : View>(
    private val viewCreation: (Context) -> V
) : DialogFragment() {

    private var _view: V? = null

    fun requireTypedView() = _view ?: error("View accessed before onCreateDialog or after onDestroyView")

    abstract fun onCreateDialog(view: V, savedInstanceState: Bundle?): Dialog

    final override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _view = viewCreation(requireContext())
        return onCreateDialog(requireTypedView(), savedInstanceState)
    }

    open fun onDestroyView(view: V) = Unit

    final override fun onDestroyView() {
        onDestroyView(requireTypedView())
        _view = null
        super.onDestroyView()
    }

}