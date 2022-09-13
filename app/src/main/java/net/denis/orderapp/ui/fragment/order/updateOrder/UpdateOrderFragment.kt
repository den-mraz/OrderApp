package net.denis.orderapp.ui.fragment.order.updateOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update_order.*
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentUpdateOrderBinding
import net.denis.orderapp.ui.fragment.dashboard.DashboardViewModel
import net.denis.orderapp.util.Helper
import net.denis.data.model.room.entities.OrderDbEntity

class UpdateOrderFragment : Fragment(), Helper {

    private lateinit var binding: FragmentUpdateOrderBinding

    private lateinit var dashboardViewModel : DashboardViewModel

    private val args by navArgs<UpdateOrderFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateOrderBinding.inflate(inflater, container, false)

        binding.editTextUpdateNameProduct.setText(args.order.nameProduct)
        binding.editTextUpdateCustomerName.setText(args.order.customerName)
        binding.editTextUpdatePhoneNumber.setText(args.order.phoneNumber)
        binding.textViewUpdateDateStart.setText(args.order.dateStart)
        binding.editTextUpdateCorporation.setText(args.order.corporation)
        binding.editTextUpdateNote.setText(args.order.note)
        binding.editTextUpdatePrepayment.setText(args.order.prepayment.toString())
        binding.editTextUpdateAmount.setText(args.order.amount.toString())

        binding.btnUpdateOrder.setOnClickListener{
            updateItem()
        }

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        return binding.root
    }

    private fun updateItem(){
        val nameProduct = editTextUpdateNameProduct.text.toString()
        val customerName = editTextUpdateCustomerName.text.toString()
        val phoneNumber = editTextUpdatePhoneNumber.text.toString()
        val corporation = editTextUpdateCorporation.text.toString()
        val note = editTextUpdateNote.text.toString()
        val prepayment = editTextUpdatePrepayment.text.toString()
        val amount = editTextUpdateAmount.text.toString()

        if (inputCheck(nameProduct, customerName, phoneNumber, prepayment, amount)){
            val updatedOrder = OrderDbEntity(
                args.order.id,
                nameProduct,
                customerName,
                phoneNumber,
                dateStart = args.order.dateStart,
                corporation,
                note,
                prepayment.toDouble(),
                amount.toDouble(),
                dateEnd = args.order.dateEnd,
                status = false
            )
            dashboardViewModel.updateOrder(updatedOrder)
            makeToast(requireContext(), "Заказ изменён")
            findNavController().navigate(R.id.action_updateOrderFragment_to_dashboardFragment)
        } else {
            makeToast(requireContext(), "Заполните все поля")
        }
    }

}