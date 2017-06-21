package id.kotlin.sample.room.main

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kotlin.sample.room.R
import id.kotlin.sample.room.data.User
import id.kotlin.sample.room.main.MainAdapter.MainHolder
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter constructor(private var users: List<User>,
                              private var listener: MainListener) : RecyclerView.Adapter<MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainHolder = MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_main, parent, false))

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val user = users[holder.adapterPosition]
        holder.bind(user, listener)
    }

    override fun getItemCount(): Int = users.size

    class MainHolder constructor(itemView: View) : ViewHolder(itemView) {

        fun bind(user: User,
                 listener: MainListener) {
            with(user) {
                val firstName = user.firstName
                val lastName = user.lastName

                itemView.textItemMain.text = firstName.plus(" ").plus(lastName)
                itemView.layoutItemMain.setOnClickListener { listener.onItemClick(user) }
                itemView.layoutItemMain.setOnLongClickListener { listener.onItemLongClick(user); true }
            }
        }
    }
}