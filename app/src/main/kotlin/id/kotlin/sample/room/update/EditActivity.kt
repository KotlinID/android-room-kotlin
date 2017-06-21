package id.kotlin.sample.room.update

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import id.kotlin.sample.room.R
import id.kotlin.sample.room.Room
import id.kotlin.sample.room.data.User
import id.kotlin.sample.room.data.UserModel
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast

class EditActivity : AppCompatActivity() {

    private lateinit var data: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        toolbarEdit.title = title
        toolbarEdit.navigationIcon = ContextCompat.getDrawable(this, R.drawable.bg_arrow_back)
        setSupportActionBar(toolbarEdit)
        loadUser()
        editButtonListener()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadUser() {
        data = intent.getParcelableExtra<UserModel>("Data")
        textFirstname.setText(data.firstName)
        textLastname.setText(data.lastName)
    }

    private fun editButtonListener() {
        btnEdit.setOnClickListener {
            val firstName = textFirstname.text.toString()
            val lastName = textLastname.text.toString()

            if (firstName.isNotEmpty().and(lastName.isNotEmpty())) {
                async(UI) {
                    bg {
                        val dao = Room.database.userDao()
                        val user = User(data.id, firstName, lastName)
                        dao.updateUser(user)
                    }

                    setResult(RESULT_OK)
                    finish()
                }
            } else {
                toast(ctx.getString(R.string.message_create_user))
            }
        }
    }
}