package com.simple.banksystem2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: ArrayList<Customer>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var itemOnClick: ((Customer) -> Unit)? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_recycleview, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.name.text = ItemsViewModel.name
        holder.balance.text = ItemsViewModel.balance.toString()
    //    holder.id.text = ItemsViewModel.id.toString()
     //   holder.Email.text = ItemsViewModel.email
        holder.itemView.setOnClickListener {
        itemOnClick?.invoke(ItemsViewModel)
        }
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Calls the views for adding it to Texts
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.nameView)
        val balance: TextView = itemView.findViewById(R.id.balanceView)
        val id: TextView = itemView.findViewById(R.id.viewID)
        val Email: TextView = itemView.findViewById(R.id.emailview)

    }
}
