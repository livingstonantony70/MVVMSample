package com.nkcfoodsapps.mvvmsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import com.nkcfoodsapps.mvvmsample.R
import com.nkcfoodsapps.mvvmsample.databinding.ActivityEmptyBinding
import com.nkcfoodsapps.mvvmsample.utils.Resource
import com.nkcfoodsapps.mvvmsample.viewmodel.DataViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class EmptyActivity : AppCompatActivity() {

    val TAG = javaClass.name
    val viewModel: DataViewModel by viewModels()

    private var binding: ActivityEmptyBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        viewModel.employees.observe(this){
            Log.d(TAG, "onCreate: $it")
            when(it.status){
                Resource.Status.LOADING -> {
                    binding?.myTextView?.text = "Loading.."
                    Log.d(TAG, "loading...")
//                    binding.progressBarogressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding?.myTextView?.text = "${it.data?.message}"
                    Log.d(TAG, "success: ${it.data}")
//                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    binding?.myTextView?.text = "Success: ${it.message}"
                    Log.d(TAG, "error: ${it.message}")
//                    binding.progressBar.visibility = View.GONE
                }
            }
        }


        binding?.myButton?.setOnClickListener {
            viewModel.getEmployees()
        }
    }
}