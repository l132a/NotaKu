package id.smartdev.notaku.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.smartdev.notaku.R
import id.smartdev.notaku.databinding.ActivityMainBinding
import id.smartdev.notaku.base.BaseActivity
import id.smartdev.notaku.ui.customer.CustomerFragment
import id.smartdev.notaku.ui.home.HomeFragment
import id.smartdev.notaku.ui.product.ProductFragment

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, ViewModel>() {
    override fun getViewModelBindingVariable(): Int = NO_VIEW_MODEL_BINDING_VARIABLE

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        getDataBinding().bottomNav.setOnNavigationItemSelectedListener { m ->
            when (m.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.nav_customer -> {
                    loadFragment(CustomerFragment())
                }
                R.id.nav_product -> {
                    loadFragment(ProductFragment())
                }
            }
            true
        }
        getDataBinding().bottomNav.selectedItemId = R.id.nav_home
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }

}