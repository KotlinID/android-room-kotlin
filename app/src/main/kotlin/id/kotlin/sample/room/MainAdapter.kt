package id.kotlin.sample.room

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.kotlin.sample.room.MainAdapter.MainHolder
import id.kotlin.sample.room.data.User
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter constructor(private var users: List<User>) : RecyclerView.Adapter<MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainHolder = MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_main, parent, false))

    override fun onBindViewHolder(holder: MainHolder?, position: Int) {
        val user = users[position]
        holder?.bind(user)
    }

    override fun getItemCount(): Int = users.size

    class MainHolder constructor(itemView: View) : ViewHolder(itemView) {

        fun bind(user: User) {
            with(user) {
                val firstName = user.firstName
                val lastname = user.lastName

                itemView.tvItemMain.text = firstName.plus(" ").plus(lastname)
            }
        }
    }
}