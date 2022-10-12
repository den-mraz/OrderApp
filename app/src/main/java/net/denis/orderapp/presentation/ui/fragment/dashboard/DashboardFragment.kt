package net.denis.orderapp.presentation.ui.fragment.dashboard

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentDashboardBinding
import net.denis.orderapp.presentation.ui.adapter.dashboard.DashboardAdapter
import net.denis.orderapp.common.SDF
import java.util.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel by viewModels<DashboardViewModel>()
  //  private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DashboardAdapter()
        val recyclerView = binding.rvDashboardFragment
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

      // dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        dashboardViewModel.dashboardData.observe(viewLifecycleOwner, Observer { dashboard ->
            adapter.setData(dashboard)
        })


        /**
         * Swipe to delete order
         * */
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
                builder.setMessage("Вы действительно хотите удалить этот заказ?")
                builder.setPositiveButton("Да") { _, _ ->
                    dashboardViewModel.deleteOrder(order)
                }
                builder.setNegativeButton("Нет") { _, _ ->
                    dashboardViewModel.dashboardData.observe(
                        viewLifecycleOwner,
                        Observer { dashboard ->
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
                    dashboardViewModel.dashboardData.observe(
                        viewLifecycleOwner,
                        Observer { dashboard ->
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

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuBtnCreateNewOrder -> findNavController().navigate(R.id.action_dashboardFragment_to_createNewOrderFragment)
        }
        return true
    }

}