package com.anugrah.logintest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anugrah.logintest.R
import com.anugrah.logintest.model.Networks

class NetworksListAdapter(var networks: ArrayList<Networks>) : RecyclerView.Adapter<NetworksListAdapter.NetworkViewHolder>() {

    fun updateCountries(newNetworks: List<Networks>){
        networks.clear()
        networks.addAll(newNetworks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= NetworkViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ip, parent, false)
    )

    override fun onBindViewHolder(holder: NetworkViewHolder, position: Int) {
        holder.bind(networks[position])
    }

    override fun getItemCount()= networks.size

    class NetworkViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val NwName = view.name
        private val NwIp = view.ip
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(networks: Networks){
            nwName.text = networks.nwName
            nwIP.text = networks.nwIP
        }
    }
}