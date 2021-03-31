package id.smartdev.notaku.ui.product

import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.smartdev.notaku.R
import id.smartdev.notaku.base.BaseFragment
import id.smartdev.notaku.databinding.FragmentProductBinding

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding, ViewModel>() {
    override fun getViewModelBindingVariable(): Int = NO_VIEW_MODEL_BINDING_VARIABLE

    override fun getLayoutId(): Int = R.layout.fragment_product

    override fun init() {

    }
}