package net.denis.orderapp.ui.fragment.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentArchiveBinding
import net.denis.orderapp.ui.adapter.archive.ArchiveAdapter
import net.denis.data.model.room.entities.OrderDbEntity

class ArchiveFragment : Fragment() {

    private lateinit var binding: FragmentArchiveBinding
    private lateinit var archiveViewModel: ArchiveViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchiveBinding.inflate(inflater, container, false)

        val adapter = ArchiveAdapter()
        val recyclerView = binding.rvArchiveFragment
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        archiveViewModel = ViewModelProvider(this).get(ArchiveViewModel::class.java)
        archiveViewModel.readVMArchive.observe(viewLifecycleOwner, Observer { archive ->
            adapter.setData(archive)
        })


        /** Swipe to move order from archive in dashboard */
        val itemTouchHelperMoveToDashboardCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                rW: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                var order = adapter.archiveList[position]
                order = OrderDbEntity(
                    id = order.id,
                    nameProduct = order.nameProduct,
                    customerName = order.customerName,
                    phoneNumber = order.phoneNumber,
                    dateStart = order.dateStart,
                    corporation = order.corporation,
                    note = order.note,
                    prepayment = order.prepayment,
                    amount = order.amount,
                    dateEnd = order.dateEnd,
                    status = false
                )

                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Перемещение")
                builder.setMessage("Вы действительно хотите вернуть этот заказ на доску?")
                builder.setPositiveButton("Да") { _, _ ->
                    archiveViewModel.moveInDashboard(order)
                }
                builder.setNegativeButton("Нет") { _, _ ->
                    archiveViewModel.readVMArchive.observe(viewLifecycleOwner, Observer { archive ->
                        adapter.setData(archive)
                    })
                }
                builder.create().show()
            }
        }

        ItemTouchHelper(itemTouchHelperMoveToDashboardCallback).apply {
            attachToRecyclerView(binding.rvArchiveFragment)
        }
        return binding.root
    }

}