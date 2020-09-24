package com.example.parayo.api.response

data class InquiryResponse(
    val id: Long,
    val question: String,
    val answer: String?,
    val requestUserName: String,
    val requestUserId: Long,
    val productOwnerName: String,
    val productOwnerId: Long,
    val productName: String,
    val productId: Long
)
