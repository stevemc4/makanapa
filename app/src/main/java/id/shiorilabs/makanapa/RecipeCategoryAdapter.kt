package id.shiorilabs.makanapa

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeCategoryAdapter : RecyclerView.Adapter<RecipeCategoryAdapter.GridViewHolder> {
    private var categories: ArrayList<RecipeCategory>
    private val layoutInflater: LayoutInflater

    constructor(context: Context): super() {
        this.layoutInflater = LayoutInflater.from(context)
        this.categories = ArrayList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeCategoryAdapter.GridViewHolder {
        val view = layoutInflater.inflate(R.layout.recipe_category_item, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeCategoryAdapter.GridViewHolder, position: Int) {
        val recipe = categories[position]
        Glide.with(holder.itemView).load(recipe.imageUrl).into(holder.background)
        holder.name.text = recipe.name
        holder.id = recipe.id
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun setCategories(categories: ArrayList<RecipeCategory>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    class GridViewHolder : RecyclerView.ViewHolder {
        var background: ImageView
        var name: TextView
        var id = "0"

        constructor(view: View) : super(view) {
            background = view.findViewById(R.id.category_background)
            name = view.findViewById(R.id.category_name)
            background.setOnClickListener { onClick(view) }
            name.setOnClickListener { onClick(view) }
        }

        private fun onClick(view: View) {
            val intent = Intent(view.context, CategoryActivity::class.java).apply {
                putExtra("categoryId", name.id)
                putExtra("categoryName", name.text)
            }
            startActivity(view.context, intent, null)
        }
    }

}