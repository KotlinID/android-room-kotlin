package id.kotlin.sample.room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import id.kotlin.sample.room.data.User
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain.title = title

        doAsync {
            val dao = Room.database.userDao()
            val users = dao.findAll()
            if (users.isEmpty()) {
                val user = User(0L, "Budi", "Oktaviyan Suryanto")
                dao.createUser(user)
            }

            uiThread {
                toast("Total user: ${users.size}")

                val layoutManager = LinearLayoutManager(this@MainActivity)
                val adapter = MainAdapter(users)
                itemMain.layoutManager = layoutManager
                itemMain.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }
}