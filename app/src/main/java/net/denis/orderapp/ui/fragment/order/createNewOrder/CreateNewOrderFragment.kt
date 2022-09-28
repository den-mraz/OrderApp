package net.denis.orderapp.ui.fragment.order.createNewOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentCreateNewOrderBinding
import net.denis.orderapp.util.SDF
import net.denis.data.model.room.entities.order.OrderDbEntity
import net.denis.orderapp.util.Helper
import java.util.Date

class CreateNewOrderFragment : Fragment(), Helper {

    private lateinit var binding: FragmentCreateNewOrderBinding

    private lateinit var createNewOrderViewModel: CreateNewOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNewOrderBinding.inflate(
            inflater,
            container,
            false
        )

        createNewOrderViewModel = ViewModelProvider(this).get(CreateNewOrderViewModel::class.java)
        binding.btnCreateNewOrder.setOnClickListener {
            insertDataToDB()
        }
        return binding.root
    }

    private fun insertDataToDB() {
        val nameProduct = binding.editTextProductName.text.toString()
        val fio = binding.editTextCustomerName.text.toString()
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val corporation = binding.editTextCorporation.text.toString()
        val note = binding.editTextNote.text.toString()
        val prepayment = binding.editTextPrepayment.text.toString()
        val amount = binding.editTextAmount.text.toString()

        try {
            val orderDbEntity = OrderDbEntity(
                0,
                nameProduct,
                fio,
                phoneNumber,
                dateStart = SDF.format(Date()),
                corporation,
                note,
                prepayment.toDouble(),
                amount.toDouble(),
                dateEnd = SDF.format(Date()),
                status = false
            )

            createNewOrderViewModel.createNewOrder(orderDbEntity)
            makeToast(requireContext(), "Заказ создан")
            findNavController().navigate(R.id.action_createNewOrderFragment_to_dashboardFragment)

        } catch (e: Exception) {
            makeToast(requireContext(), "Заполните данные")
        }
    }

}