package net.denis.orderapp.di

import dagger.Subcomponent
import net.denis.orderapp.presentation.ui.fragment.archive.ArchiveFragment
import net.denis.orderapp.presentation.ui.fragment.dashboard.DashboardFragment
import net.denis.orderapp.presentation.ui.fragment.order.createNewOrder.CreateNewOrderFragment
import net.denis.orderapp.presentation.ui.fragment.order.updateOrder.UpdateOrderFragment

@Subcomponent
interface FragmentModule {

    fun injectDashboardFragment(fragment: DashboardFragment)
    fun injectArchiveFragment(fragment: ArchiveFragment)
    fun injectCreateNewOrderFragment(fragment: CreateNewOrderFragment)
    fun injectUpdateOrderFragment(fragment: UpdateOrderFragment)

}