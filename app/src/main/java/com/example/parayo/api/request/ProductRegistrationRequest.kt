package com.example.parayo.api.request

data class ProductRegistrationRequest(
    val name: String?,
    val description: String?,
    val price: Int?,
    val categoryId: Int?,
    val imageIds: List<Long?>
) {

    val isNotValidName get() = name?.length !in 1..40
    val isNotValidDescription get() = description?.length !in 1..500
    val isNotValidPrice get() = price?.let { it < 1 } ?: false
    val isNotValidCategoryId get() = categoryId == null
    val isNotValidImageIds get() = imageIds.filterNotNull().isEmpty()

}