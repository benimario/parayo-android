package com.example.parayo.product.category

data class Category(
    val id: Int,
    val name: String
)

val categoryList = listOf(
    Category(0, "여성의류"),
    Category(1, "남성의류"),
    Category(2, "가전제품"),
    Category(3, "생활용품"),
    Category(4, "도서"),
    Category(5, "반려동물용품"),
    Category(6, "식품")
)