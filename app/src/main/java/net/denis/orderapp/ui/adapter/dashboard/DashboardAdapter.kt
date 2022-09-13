package net.denis.orderapp.ui.adapter.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout_dashboard.view.*
import net.denis.orderapp.R
import net.denis.orderapp.ui.fragment.dashboard.DashboardFragmentDirections
import net.denis.data.model.room.entities.OrderDbEntity

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    var dashboardList = emptyList<OrderDbEntity>()

    class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_dashboard, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int = dashboardList.size

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val currentDashboardItem = dashboardList[position]
        holder.itemView.apply {
            item_dashboard_nameProduct.text = currentDashboardItem.nameProduct
            item_dashboard_customerName.text = currentDashboardItem.customerName
            item_dashboard_phoneNumber.text = currentDashboardItem.phoneNumber

           holder.itemView.item_dashboard.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardFragmentToUpdateOrderFragment(currentDashboardItem)
                holder.itemView.findNavController().navigate(action)
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dashboard: List<OrderDbEntity>) {
        this.dashboardList = dashboard
        notifyDataSetChanged()
    }

}