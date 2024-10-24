package com.example.campusrootsinternapp.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.campusrootsinternapp.util.observeChange
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseFragment() : Fragment(), CoroutineScope, SaveData {
    lateinit var mFragmentNavigation: FragmentNavigation
//    @Inject
//    lateinit var sharedPreference: SharedPreference

    private val backgroundJobs: CompletableJob =  Job()
    var hasWindowChanged = false
    var currentFullscreenHostFragment: BaseFragment? = null

    override val coroutineContext: CoroutineContext
        get() = backgroundJobs + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
//            sharedPreference = SharedPreference(context = context )
            mFragmentNavigation = context
        }
    }

    override fun onResume() {
        super.onResume()
//        hideKeyboard()

    }

    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
        fun popFragment()
        fun popFragments(n:Int)
        fun openDialogFragment(fragment:DialogFragment)
        fun openBottomDialogFragment(bottomSheetDialogFragment: BottomSheetDialogFragment?)
        fun openBottomSheet(bottomSheetDialogFragment: BottomSheetDialogFragment)
        fun clearStack()
        fun clearDialogFragmentStack()
        fun switchFragment(index: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAllJobs()
    }

    private fun cancelAllJobs(){
        try{
            backgroundJobs.cancel()
        }catch (ex:Exception){

        }
    }

    fun setUpObservers(viewModel: BaseViewModel){
        setUpErrorHandler(viewModel)
        setUpLoading(viewModel)
    }

    private fun setUpErrorHandler(viewModel: BaseViewModel){
        viewModel.showError.observeChange(this){showToast(it)}
    }

    private fun setUpLoading(viewModel: BaseViewModel){
        viewModel.showLoading.observeChange(this){status -> showLoading(status)}
    }

    fun showLoading(status:Boolean){
//        when(val activity = requireActivity()){
//            is IntroActivity -> activity.showLoading(status)
//            is MainActivity -> activity.showLoading(status)
//        }
    }


    fun showNavigationBar(toShow:Boolean, title: String?="", canGoBack:Boolean = true) {
        if (toShow) {
            (activity as BaseActivity?)?.supportActionBar?.show()
            (activity as BaseActivity?)?.supportActionBar?.title = title
            (activity as BaseActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
            (activity as BaseActivity?)?.supportActionBar?.setHomeButtonEnabled(canGoBack)
        } else (activity as BaseActivity?)?.supportActionBar?.hide()
    }


    fun showInputDialog(title:String, successMessage:String, hint:String? = "Reason", buttonText:String = "Okay", action: (String) -> Unit, enforceTextField: Boolean?=false){
/*        mFragmentNavigation.openDialogFragment(InputDialogFragment.newInstance(
            successTitle = title,
            successMessage = successMessage,
            hint = hint,
            buttonText = buttonText,
            enforceTextField = enforceTextField,
            action = action
        ))*/
    }



    fun showToast(errorMessage:String){
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    fun showAlert(
        title: String,
        message: String,
        action: () -> Unit?= {  },
        positiveButton: String? = "Yes"){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButton,
            DialogInterface.OnClickListener { dialog, which ->
                action()
            })
        builder.create().show()
    }

    fun checkIfFragmentAttached(operation: Context.() -> Unit) {
        if (isAdded && context != null) {
            operation(requireContext())
        }
    }

    override fun saveToViewModel() {

    }

}

interface SaveData {
    fun saveToViewModel()
}
