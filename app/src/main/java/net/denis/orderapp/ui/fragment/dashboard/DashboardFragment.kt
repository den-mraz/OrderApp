package net.denis.orderapp.ui.fragment.dashboard

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentDashboardBinding
import net.denis.orderapp.ui.adapter.dashboard.DashboardAdapter
import net.denis.orderapp.util.SDF
import net.denis.data.model.room.entities.OrderDbEntity
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        val adapter = DashboardAdapter()
        val recyclerView = binding.rvDashboardFragment
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        /** Swipe to delete order*/
        val itemTouchHelperDeleteCallback = object : ItemTouchHelper.SimpleCallback(
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
                val order = adapter.dashboardList[position]
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Удаление")
                builder.setMessage("Вы действительно хотите удалить заказ?")
                builder.setPositiveButton("Да") { _, _ ->
                    dashboardViewModel.deleteOrder(order)
                }
                builder.setNegativeButton("Нет") { _, _ ->
                    dashboardViewModel.readVMDashboard.observe(viewLifecycleOwner, Observer { dashboard ->
                        adapter.setData(dashboard)
                    })
                }
                builder.create().show()
            }
        }

        /** Swipe to move order from dashboard into archive */
        val itemTouchHelperMoveToArchiveCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT
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
                var order = adapter.dashboardList[position]
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
                    dateEnd = SDF.format(Date()),
                    status = true
                )

                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Перемещение")
                builder.setMessage("Вы действительно хотите отправить этот заказ в архив?")
                builder.setPositiveButton("Да") { _, _ ->
                    dashboardViewModel.updateOrder(order)
                }
                builder.setNegativeButton("Нет") { _, _ ->
                    dashboardViewModel.readVMDashboard.observe(viewLifecycleOwner, Observer { dashboard ->
                        adapter.setData(dashboard)
                    })
                }
                builder.create().show()
            }
        }

        ItemTouchHelper(itemTouchHelperDeleteCallback).apply {
            attachToRecyclerView(binding.rvDashboardFragment)
        }.also {
            ItemTouchHelper(itemTouchHelperMoveToArchiveCallback).apply {
                attachToRecyclerView(binding.rvDashboardFragment)
            }
        }

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel.readVMDashboard.observe(viewLifecycleOwner, Observer { dashboard ->
            adapter.setData(dashboard)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuBtnCreateNewOrder -> {
                findNavController().navigate(R.id.action_dashboardFragment_to_createNewOrderFragment)
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }
}