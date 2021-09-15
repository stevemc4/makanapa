package id.shiorilabs.makanapa

import com.google.gson.annotations.SerializedName

data class RecipeCategory (
    @SerializedName("idCategory")
    val id: String,

    @SerializedName("strCategory")
    val name: String,

    @SerializedName("strCategoryThumb")
    val imageUrl: String,

    @SerializedName("strCategoryDescription")
    val description: String
    )
