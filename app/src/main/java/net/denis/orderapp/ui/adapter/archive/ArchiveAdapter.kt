package net.denis.orderapp.ui.adapter.archive

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout_archive.view.*
import net.denis.orderapp.R
import net.denis.orderapp.ui.fragment.archive.ArchiveFragmentDirections
import net.denis.orderapp.util.Helper
import net.denis.data.model.room.entities.OrderDbEntity

class ArchiveAdapter : RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>(), Helper {

    var archiveList = emptyList<OrderDbEntity>()

    class ArchiveViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_archive, parent, false)
        return ArchiveViewHolder(view)
    }

    override fun getItemCount(): Int = archiveList.size

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        val currentArchiveOrder = archiveList[position]
        holder.itemView.apply {
            item_archive_nameProduct.text = currentArchiveOrder.nameProduct
            item_archive_customerName.text = currentArchiveOrder.customerName
            item_archive_phoneNumber.text = currentArchiveOrder.phoneNumber
            item_archive_amount.text = currentArchiveOrder.amount.toString()

            holder.itemView.item_archive.setOnClickListener {
                val action = ArchiveFragmentDirections.actionArchiveFragmentToOpenOrderFromArchiveFragment(currentArchiveOrder)
                holder.itemView.findNavController().navigate(action)
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(archive: List<OrderDbEntity>) {
        this.archiveList = archive
        notifyDataSetChanged()
    }

}