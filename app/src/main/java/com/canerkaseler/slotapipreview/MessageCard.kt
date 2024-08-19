package com.canerkaseler.slotapipreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageCard(
    messageData: MessageCardSlot.Parameters,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .size(size = 300.dp), // This is added to simplify.
        colors = CardColors(
            containerColor = Color.LightGray,
            contentColor = Color.LightGray,
            disabledContainerColor = Color.DarkGray,
            disabledContentColor = Color.DarkGray,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painterResource(id = messageData.icon),
                    contentDescription = "Dialog icon.",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(72.dp),
                )
                Text(
                    text = messageData.title,
                    fontSize = 24.sp,
                    style = TextStyle(fontWeight = FontWeight.Black),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 24.dp)
                )

                Text(
                    text = messageData.description,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 16.dp)
                )
            }

            Column (
                modifier = Modifier
                    .padding(start = 24.dp, bottom = 16.dp)
                    .align(
                        alignment = Alignment.BottomStart
                    ),
            ) {
                Button(
                    colors = ButtonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.Blue,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray,
                    ),
                    onClick = messageData.onClickPositiveButton,
                    content = {
                        Text(
                            text = messageData.positiveButtonText,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier
                        )
                    }
                )
                Button(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    colors = ButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Red,
                        disabledContentColor = Color.Gray,
                        disabledContainerColor = Color.Gray,
                    ),
                    onClick = messageData.onClickNegativeButton,
                    content = {
                        Text(
                            text = messageData.negativeButtonText,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier
                        )
                    }
                )
            }
        }
    }
}

enum class ErrorType {
    BATTERY,
    CONNECTION,
    GENERAL,
}

sealed interface MessageCardSlot {
    data class Parameters(
        val icon: Int,
        val title: String,
        val description: String,
        val positiveButtonText: String,
        val negativeButtonText: String,
        val onClickPositiveButton: () -> Unit,
        val onClickNegativeButton: () -> Unit,
    )
}

@Preview
@Composable
private fun Preview() {
    val messageData = MessageCardSlot.Parameters(
        icon = R.drawable.wifi_error,
        title = "Connection Problem",
        description = "There is an internet connection error.",
        positiveButtonText = "Report an issue",
        negativeButtonText = "Cancel",
        onClickPositiveButton = {},
        onClickNegativeButton = {},
    )

    MessageCard(
        modifier = Modifier
            .size(size = 300.dp),
        messageData = messageData,
    )
}
