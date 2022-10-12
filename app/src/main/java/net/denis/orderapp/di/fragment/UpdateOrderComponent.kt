package net.denis.orderapp.di.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import net.denis.orderapp.di.FragmentModule
import net.denis.orderapp.presentation.ui.fragment.order.updateOrder.UpdateOrderFragment

open class UpdateOrderComponent: Fragment() {

    lateinit var fragmentModule: FragmentModule

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentModule.injectUpdateOrderFragment(this as UpdateOrderFragment)
    }

}