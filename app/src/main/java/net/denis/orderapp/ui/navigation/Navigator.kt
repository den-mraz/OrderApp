package net.denis.orderapp.ui.navigation

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showDashboard()

    fun showArchive()

    fun createNewOrder()

    fun openOrder()

    fun goBack()
}