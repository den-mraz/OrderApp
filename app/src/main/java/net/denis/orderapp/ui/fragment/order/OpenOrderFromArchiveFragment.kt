package net.denis.orderapp.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import net.denis.orderapp.ui.fragment.archive.ArchiveViewModel
import net.denis.orderapp.databinding.FragmentArchiveItemDetailBinding

class OpenOrderFromArchiveFragment : Fragment() {

    private lateinit var binding: FragmentArchiveItemDetailBinding

    private lateinit var archiveViewModel: ArchiveViewModel

    private val args by navArgs<OpenOrderFromArchiveFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchiveItemDetailBinding.inflate(inflater, container, false)

        binding.itemOrderNameProduct.text = args.orderFromArchive.nameProduct
        binding.itemOrderCustomerName.text = args.orderFromArchive.customerName
        binding.itemOrderPhoneNumber.text = args.orderFromArchive.phoneNumber
        binding.itemOrderDateStart.text = args.orderFromArchive.dateStart
        binding.itemOrderCorporation.text = args.orderFromArchive.corporation
        binding.itemOrderNote.text = args.orderFromArchive.note
        binding.itemOrderPrepayment.text = args.orderFromArchive.prepayment.toString()
        binding.itemOrderAmount.text = args.orderFromArchive.amount.toString()
        binding.itemOrderDateEnd.text = args.orderFromArchive.dateEnd

        archiveViewModel = ViewModelProvider(this).get(ArchiveViewModel::class.java)

        return binding.root
    }

}