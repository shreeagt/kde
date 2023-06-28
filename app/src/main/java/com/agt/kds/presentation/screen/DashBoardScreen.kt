package com.agt.kds.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agt.kds.data.Order
import com.agt.kds.data.OrderStatus
import com.agt.kds.presentation.util.inKdsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen() {

    val orders = remember {
        mutableStateListOf<Order>().apply {
            addAll(demoOrder)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Kde System") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            LazyColumn {

                items(orders.filter { it.status != OrderStatus.REJECTED }
                    .sortedByDescending { it.status == OrderStatus.ACCEPT }) {
                    OrderItem(order = it,
                        onAccept = { order ->
                            orders.remove(order)
                            orders.add(order.copy(status = OrderStatus.ACCEPT))

                        },
                        onReject = {order->
                            orders.remove(order)
                            orders.add(order.copy(status = OrderStatus.REJECTED))
                        }
                    )
                }

            }

        }
    }


}

@Composable
fun OrderItem(order: Order, onAccept: (Order) -> Unit, onReject: (Order) -> Unit) {
    Card(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = order.orderName)
            Text(text = "qnt : ${order.qnt}")
            if(order.status == OrderStatus.ACCEPT){
                Text(text = "Done")
            }
            if (order.status == OrderStatus.NOT_HANDELED) {
                Row { 
                    Button(
                        onClick = { onReject(order) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = { onAccept(order) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Green.copy(green = 0.5f),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Process")
                    }
                }
            }

        }
    }
}


@Preview
@Composable
fun DashBoard_Preview() {

    inKdsTheme {
        DashBoardScreen()
    }

}

val demoOrder = listOf(
    Order(
        2,
        "Ruti",
        2,
        OrderStatus.ACCEPT
    ),
    Order(
        2,
        "Ice Cream",
        2,
        OrderStatus.NOT_HANDELED
    ),
    Order(
        2,
        "Chicken",
        1,
        OrderStatus.NOT_HANDELED
    ),
    Order(
        2,
        "Pokora",
        5,
        OrderStatus.NOT_HANDELED
    )
)