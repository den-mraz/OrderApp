package net.denis.orderapp.presentation.ui.fragment.order.createNewOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentCreateNewOrderBinding
import net.denis.orderapp.common.SDF
import net.denis.orderapp.data.local.order.room.entities.order.OrderDbEntity
import net.denis.orderapp.common.makeToast
import java.util.Date

@AndroidEntryPoint
class CreateNewOrderFragment : Fragment() {

    private lateinit var binding: FragmentCreateNewOrderBinding

    private val createNewOrderViewModel by viewModels<CreateNewOrderViewModel>()

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
            makeToast(requireContext(), "$e")
        }
    }

}