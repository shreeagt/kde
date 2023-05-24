package com.agt.kds.data

data class Order(
    val orderId : Int,
    val orderName : String,
    val qnt : Int,
    val status: OrderStatus
)

enum class OrderStatus {
    ACCEPT,REJECTED,NOT_HANDELED
}