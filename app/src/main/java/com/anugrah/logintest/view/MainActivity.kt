package com.anugrah.logintest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anugrah.logintest.R
import com.anugrah.logintest.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val networksAdapter = NetworksListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        ipList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = networksAdapter
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.networks.observe(this, Observer { networks ->
            ipList.visibility = View.VISIBLE
            networks?.let { networksAdapter.updateCountries(it) }
        })

        viewModel.networksLoadError.observe(this, Observer { isError ->
            isError?.let{list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if(it){
                    list_error.visibility = View.GONE
                    ipList.visibility = View.GONE
                }
            }
        })
    }
}