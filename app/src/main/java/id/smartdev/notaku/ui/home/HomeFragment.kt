package id.smartdev.notaku.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.smartdev.notaku.R
import id.smartdev.notaku.databinding.FragmentHomeBinding
import id.smartdev.notaku.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun getViewModelBindingVariable(): Int = NO_VIEW_MODEL_BINDING_VARIABLE

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun init() {
        try {
            viewModel.getCustomers().observe(viewLifecycleOwner) { cus ->
                if (cus.isNotEmpty()) {
                    getDataBinding().tvCustomer.text = cus.size.toString()
                }
            }
            viewModel.loadData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

}