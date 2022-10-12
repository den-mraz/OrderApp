package net.denis.orderapp.presentation.ui.fragment.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.databinding.FragmentArchiveBinding
import net.denis.orderapp.presentation.ui.adapter.archive.ArchiveAdapter

@AndroidEntryPoint
class ArchiveFragment : Fragment() {

    private lateinit var binding: FragmentArchiveBinding
    private val archiveViewModel by viewModels<ArchiveViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchiveBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ArchiveAdapter()
        val recyclerView = binding.rvArchiveFragment
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        archiveViewModel.archiveData.observe(viewLifecycleOwner, Observer { archive ->
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
                    archiveViewModel.archiveData.observe(viewLifecycleOwner, Observer { archive ->
                        adapter.setData(archive)
                    })

                }
                builder.create().show()
            }
        }

        ItemTouchHelper(itemTouchHelperMoveToDashboardCallback).apply {
            attachToRecyclerView(binding.rvArchiveFragment)
        }
    }


}