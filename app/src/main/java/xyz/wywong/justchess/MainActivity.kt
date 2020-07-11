package xyz.wywong.justchess

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fair.myfirstlibrary.testphase1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

   }

    fun startNewSinglePersonGame(view: View) {
        val intent = Intent(this, SinglePersonGameActivity::class.java)
        startActivity(intent)
    }

    fun startNewTwoPersonGame(view: View) {
        val intent = Intent(this, TwoPersonGameActivity::class.java)
        startActivity(intent)
    }
}