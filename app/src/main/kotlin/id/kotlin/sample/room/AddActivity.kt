package id.kotlin.sample.room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.kotlin.sample.room.data.User
import id.kotlin.sample.room.extensions.getId
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        btnAdd.setOnClickListener {
            async(UI) {
                bg {
                    val dao = Room.database.userDao()
                    val user = User(getId(), editFirstname.text.toString(), editLastname.text.toString())
                    dao.createUser(user)
                }

                finish()
            }
        }
    }
}