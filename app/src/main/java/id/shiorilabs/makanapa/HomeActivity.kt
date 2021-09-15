package id.shiorilabs.makanapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

data class ResponseType(
    @SerializedName("categories")
    val categories: List<RecipeCategory>
)

class HomeActivity : AppCompatActivity() {
    var recipeCategoryAdapter: RecipeCategoryAdapter? = null
    val categories: ArrayList<RecipeCategory> = ArrayList()

    var progressBar: ProgressBar? = null
    var recipeCategoryList: RecyclerView? = null

    private fun fetchRecipeCategories() {
        Log.d("response", "Init Response")
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.themealdb.com/api/json/v1/1/categories.php")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("response", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val body = response.body?.string()
                    val gson = Gson()
                    val recipeCategories = gson.fromJson<ResponseType>(body, ResponseType::class.java)
                    for (category in recipeCategories.categories) {
                        categories.add(category)
                    }

                    runOnUiThread {
                        recipeCategoryAdapter!!.setCategories(categories)
                        progressBar!!.visibility = View.GONE
                        recipeCategoryList!!.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    Log.e("response", e.toString())
                }
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        progressBar = findViewById<ProgressBar>(R.id.recipe_category_progress)
        recipeCategoryList = findViewById<RecyclerView>(R.id.category_list)
        recipeCategoryList!!.layoutManager = GridLayoutManager(this, 2 )
        recipeCategoryAdapter = RecipeCategoryAdapter(this)
        recipeCategoryList!!.adapter = recipeCategoryAdapter
        recipeCategoryAdapter!!.setCategories(categories)

        fetchRecipeCategories()
    }
}