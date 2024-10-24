package com.example.campusrootsinternapp.base

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.campusrootsinternapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavLogger
import com.ncapdevi.fragnav.FragNavTransactionOptions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

open class BaseActivity : AppCompatActivity(), BaseFragment.FragmentNavigation,
    FragNavController.TransactionListener, CoroutineScope {

    lateinit var basefragNavController: FragNavController
    var backgroundJobs = Job()
    override val coroutineContext: CoroutineContext
        get() = backgroundJobs + Dispatchers.Main

    override fun setContentView(view: View?) {
        super.setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


//        _binding = ActivityBaseBinding.inflate(layoutInflater)
//        val view = _binding!!.root
//        setContentView(view)

    }

    fun initFragNavController(
        activity: BaseActivity,
        listOfRootFragments: List<BaseFragment>,
        tag: String,
        supportFragmentManager: FragmentManager,
        root: Int,
        savedInstanceState: Bundle?
    ) {
        basefragNavController = FragNavController(supportFragmentManager, root)

        basefragNavController.apply {
            transactionListener = activity
            createEager = true
            rootFragments = listOfRootFragments
            fragNavLogger = object : FragNavLogger {
                override fun error(message: String, throwable: Throwable) {
                    Timber.e(tag, message, throwable)
                }
            }
            defaultTransactionOptions =
                FragNavTransactionOptions.newBuilder().allowStateLoss(true).customAnimations(
                    R.anim.slide_in_from_right,
                    R.anim.slide_out_to_left,
                    R.anim.slide_in_from_left,
                    R.anim.slide_out_to_right,
                    ).build()
            fragmentHideStrategy = FragNavController.DETACH_ON_NAVIGATE_HIDE_ON_SWITCH
        }
        basefragNavController.initialize(FragNavController.TAB1,savedInstanceState = savedInstanceState)
    }


    override fun pushFragment(fragment: Fragment) {
        basefragNavController.pushFragment(fragment)
    }

    override fun popFragments(n: Int) {
        tryAction {
            basefragNavController.popFragments(n)
        }
    }

    override fun openDialogFragment(fragment: DialogFragment) {
        basefragNavController.showDialogFragment(fragment)
    }

    override fun openBottomDialogFragment(bottomSheetDialogFragment: BottomSheetDialogFragment?) {
        try {
            basefragNavController.showDialogFragment(bottomSheetDialogFragment)
        } catch (
            ex: Exception
        ) {
            basefragNavController.clearDialogFragment()
            basefragNavController.showDialogFragment(bottomSheetDialogFragment)
        }
    }

    override fun popFragment() {
        basefragNavController.popFragment()
    }

    override fun clearDialogFragmentStack() {
        basefragNavController.clearDialogFragment()
    }

    override fun switchFragment(index: Int) {
        basefragNavController.switchTab(index)
    }

    override fun openBottomSheet(bottomSheetFragment: BottomSheetDialogFragment) {
        try {
            basefragNavController.showDialogFragment(bottomSheetFragment)
        } catch (ex: Exception) {
            basefragNavController.clearDialogFragment()
            basefragNavController.showDialogFragment(bottomSheetFragment)
        }
    }

    override fun clearStack() {
        basefragNavController.clearStack()
    }

    override fun onFragmentTransaction(
        fragment: Fragment?,
        transactionType: FragNavController.TransactionType
    ) {
        supportActionBar?.setDisplayHomeAsUpEnabled(basefragNavController.isRootFragment.not())
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        supportActionBar?.setDisplayHomeAsUpEnabled(basefragNavController.isRootFragment.not())
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            backgroundJobs.cancel()
        } catch (ex: Exception) {
            println(ex)
        }

    }

    override fun onBackPressed() {
        if (!basefragNavController.isRootFragment) {
            if (basefragNavController.popFragment().not()) {
                super.onBackPressed()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        basefragNavController.onSaveInstanceState(outState)
    }

    private fun tryAction(action: () -> Unit) {
        return try {
            action()
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }

}