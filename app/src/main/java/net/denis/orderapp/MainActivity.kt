package net.denis.orderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.main_activity.*
import net.denis.orderapp.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigateView.setupWithNavController(fragment.findNavController())
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuBtnCreateNewOrder -> {
                findNavController(R.id.fragment).navigate(R.id.action_dashboardFragment_to_createNewOrderFragment)
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}

/**
 * Код детали
 * название
 * кол-во
 */

/*



        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.placeTabs, MenuFragment.newInstance())
                .add(R.id.placeHolder, DashboardFragment.newInstance())
                .commit()

        }
    }

    override fun openOrder() {
        launchFragment(OrderFragment.newInstance())
    }

    override fun showDashboard() {
        launchFragment(DashboardFragment.newInstance())
    }

    override fun showArchive() {
        launchFragment(ArchiveFragment.newInstance())
    }

    override fun createNewOrder() {
        launchFragment(CreateNewOrderFragment.newInstance())
    }

    override fun goBack() {
        onBackPressed()
    }


    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, fragment, "TAG")
            .addToBackStack("TAG")
            .commit()
    }
}
 */