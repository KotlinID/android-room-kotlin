package id.kotlin.sample.room

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import id.kotlin.sample.room.data.User
import id.kotlin.sample.room.extensions.getId
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        toolbarAdd.title = title
        toolbarAdd.navigationIcon = ContextCompat.getDrawable(this, R.drawable.bg_arrow_back)
        setSupportActionBar(toolbarAdd)

        btnAdd.setOnClickListener {
            val firstName = editFirstname.text.toString()
            val lastName = editLastname.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                async(UI) {
                    bg {
                        val dao = Room.database.userDao()
                        val user = User(getId(), firstName, lastName)
                        dao.createUser(user)
                    }

                    setResult(RESULT_OK)
                    finish()
                }
            } else {
                toast(ctx.getString(R.string.message_create_user))
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
    }
}