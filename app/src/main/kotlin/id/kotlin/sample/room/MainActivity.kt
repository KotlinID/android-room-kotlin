package id.kotlin.sample.room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
            val user = User(0L, "Budi", "Oktaviyan Suryanto")
            val dao = Room.database.userDao()
            dao.createUser(user)

            val users = dao.findAll()
            uiThread {
                toast("Total user: ${users.size}")
            }
        }
    }
}