package com.vortex.billreminder.storage.model

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("categories")
    val categories: List<CategoryJson>
)

data class CategoryJson(
    @SerializedName("icon_name")
    val iconName: String,
    @SerializedName("name")
    val name: String
)