package com.vortex.billreminder.service.mappers

import com.vortex.billreminder.domain.model.Category
import com.vortex.billreminder.storage.model.CategoryJson
import com.vortex.billreminder.storage.model.CategoryStorage

fun CategoryJson.mapCategoryJsonToCategoryStorage(): CategoryStorage {
    return CategoryStorage(
        name = name,
        iconName = iconName
    )
}

fun CategoryStorage.mapCategoryStorageToCategory(): Category {
    return Category(
        id = id,
        name = name,
        iconName = iconName
    )
}