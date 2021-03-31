package id.smartdev.notaku.ui.customer

import android.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import cn.pedant.SweetAlert.SweetAlertDialog
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import dagger.hilt.android.AndroidEntryPoint
import id.smartdev.notaku.R
import id.smartdev.notaku.databinding.FragmentCustomerBinding
import id.smartdev.notaku.base.BaseFragment
import id.smartdev.notaku.data.model.Customer

@AndroidEntryPoint
class CustomerFragment : BaseFragment<FragmentCustomerBinding, CustomerViewModel>() {

    private lateinit var skeletonScreen: SkeletonScreen
    private lateinit var adapter: CustomerAdapter
    private lateinit var alertDialog: AlertDialog
    private lateinit var customer: Customer
    private lateinit var sweetAlertDialog: SweetAlertDialog
    private val viewModel: CustomerViewModel by viewModels()
    private val listCustomer: ArrayList<Customer> = ArrayList()

    override fun getViewModelBindingVariable(): Int = NO_VIEW_MODEL_BINDING_VARIABLE

    override fun getLayoutId(): Int = R.layout.fragment_customer

    override fun init() {
        setHasOptionsMenu(true)

        adapter = CustomerAdapter(listCustomer) { cus ->
            onCustomer(cus)
        }

        sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText(getString(R.string.success))

        skeletonScreen = Skeleton.bind(getDataBinding().rvCustomer)
            .adapter(adapter)
            .color(R.color.light_transparent)
            .load(R.layout.item_customer_skeleton)
            .show()

        try {
            viewModel.getCustomers().observe(viewLifecycleOwner) { cus ->
                listCustomer.clear()
                listCustomer.addAll(cus)
                adapter.notifyDataSetChanged()
                if (cus.isNotEmpty())
                    getDataBinding().contentEmpty.root.visibility = View.GONE
                else getDataBinding().contentEmpty.root.visibility = View.VISIBLE
                getDataBinding().slCustomer.isRefreshing = false
                skeletonScreen.hide()
            }
            viewModel.loadCustomer()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        getDataBinding().slCustomer.setOnRefreshListener {
            skeletonScreen.show()
            viewModel.loadCustomer()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCustomer()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.add_nav, menu)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_add -> {
                onCustomer()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onCustomer(cus: Customer? = null) {
        val dialog = AlertDialog.Builder(context)
        val sheet = layoutInflater.inflate(R.layout.dialog_customer, null)
        var mode = 0
        dialog.setTitle(getString(R.string.add).plus(" ").plus(getString(R.string.customer)))
        if (cus != null) {
            dialog.setTitle(getString(R.string.edit).plus(" ").plus(getString(R.string.customer)))
            sheet.findViewById<EditText>(R.id.et_name).setText(cus.name)
            sheet.findViewById<EditText>(R.id.et_address).setText(cus.address)
            sheet.findViewById<EditText>(R.id.et_phone).setText(cus.phone)
            mode = 1
        }
        dialog.setView(sheet)
        alertDialog = dialog.show()
        sheet.findViewById<Button>(R.id.bt_cancel).setOnClickListener {
            alertDialog.dismiss()
        }
        sheet.findViewById<Button>(R.id.bt_save).setOnClickListener {
            performSave(mode, sheet, alertDialog, cus)
        }
    }

    private fun performSave(mode: Int, sheet: View?, dialog: AlertDialog?, cus: Customer?) {
        val name = sheet?.findViewById<EditText>(R.id.et_name)?.text.toString().trim()
        val address = sheet?.findViewById<EditText>(R.id.et_address)?.text.toString().trim()
        val phone = sheet?.findViewById<EditText>(R.id.et_phone)?.text.toString().trim()

        customer = Customer(name, phone, address, cus?.id)

        if (name.isNotEmpty()) {
            try {
                when (mode) {
                    0 -> {
                        viewModel.insertCustomer(customer)
                    }
                    1 -> {
                        viewModel.updateCustomer(customer)
                    }
                }
                sweetAlertDialog.show()
                dialog?.dismiss()
            } catch (e: Exception) {
                SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).show()
                e.printStackTrace()
            }
        } else SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setContentText(getString(R.string.field_error))
            .show()
    }
}