package id.shiorilabs.makanapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryName = intent.getStringExtra("categoryName")
        findViewById<TextView>(R.id.category_name_header).apply {
            text = categoryName
        }

        supportActionBar?.hide()

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            super.onBackPressed()
        }
    }
}