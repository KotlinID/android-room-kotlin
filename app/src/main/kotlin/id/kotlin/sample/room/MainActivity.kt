package id.kotlin.sample.room

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import id.kotlin.sample.room.data.User
import id.kotlin.sample.room.extensions.getId
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
        loadUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> addUser()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            200 -> loadUser()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadUser() {
        async(UI) {
            val users: Deferred<List<User>> = bg {
                getUsers()
            }

            showUsers(users.await())
        }
    }

    private fun getUsers(): List<User> {
        val dao = Room.database.userDao()
        val users = dao.findAll()

        if (users.isEmpty()) {
            val user = User(getId(), "Budi", "Oktaviyan Suryanto")
            dao.createUser(user)

            return dao.findAll()
        }

        return users
    }

    private fun showUsers(users: List<User>) {
        toast("Total user: ${users.size}")

        val layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = MainAdapter(users)
        itemMain.layoutManager = layoutManager
        itemMain.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun addUser() {
        startActivityForResult<AddActivity>(200)
    }
}