package net.denis.orderapp.ui.fragment.storage

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.custom_item_edittext_layout.view.*
import net.denis.data.model.room.entities.storage.category.CategoryToolEntity
import net.denis.orderapp.R
import net.denis.orderapp.databinding.FragmentStorageBinding
import net.denis.orderapp.ui.adapter.storage.StorageAdapter
import net.denis.orderapp.util.Helper

class StorageFragment : Fragment(), Helper {

    private lateinit var binding: FragmentStorageBinding
    private lateinit var storageViewModel: StorageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStorageBinding.inflate(inflater, container, false)
        storageViewModel = ViewModelProvider(this).get(StorageViewModel::class.java)
        setHasOptionsMenu(true)

        val adapter = StorageAdapter()
        val recyclerView = binding.rvStorageFragment
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        storageViewModel.readVMStorage.observe(viewLifecycleOwner, Observer { category ->
            adapter.setData(category)
        })

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_storage_hamburger_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnAddCategory -> {
                makeToast(requireContext(), "add category")
                addEditText()

                return false
            }
            R.id.btnAddItem -> {
                makeToast(requireContext(), "add item")
                return false
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun addEditText() {
        val view: View = layoutInflater.inflate(R.layout.custom_item_edittext_layout, null)
        val editText = view.findViewById(R.id.addNewItem) as EditText
        //val editText = EditText(requireContext())
        binding.fragmentStorage.addView(view)

        view.saveCategory.setOnClickListener {
            createAndInsertCategoryIntoDB(editText.text.toString())
        }

    }

    private fun createAndInsertCategoryIntoDB(nameCategory: String) {
        try {
            val categoryToolEntity = CategoryToolEntity(
                0,
                nameCategory
            )

            storageViewModel.createNewCategory(categoryToolEntity)
            makeToast(requireContext(), "Категория создана")
        } catch (e: Exception) {
            makeToast(requireContext(), "Заполните данные")
        }
    }

}