package net.denis.orderapp.ui.adapter.storage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout_category.view.*
import net.denis.data.model.room.entities.storage.category.CategoryToolEntity
import net.denis.orderapp.R
import net.denis.orderapp.util.Helper

class StorageAdapter : RecyclerView.Adapter<StorageAdapter.StorageViewHolder>(), Helper {

    var categoryList = emptyList<CategoryToolEntity>()

    class StorageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_category, parent, false)
        return StorageViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: StorageViewHolder, position: Int) {
        val currentCategoryItem = categoryList[position]
        holder.itemView.apply {
            item_category_name.text = currentCategoryItem.nameCategoryTool

            holder.itemView.item_category_name.setOnClickListener {
               makeToast(context, "${currentCategoryItem.id}")
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(category: List<CategoryToolEntity>) {
        this.categoryList = category
        notifyDataSetChanged()
    }

}