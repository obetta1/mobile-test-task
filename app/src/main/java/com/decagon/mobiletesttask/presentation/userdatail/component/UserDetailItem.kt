package com.decagon.mobiletesttask.presentation.userdatail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.decagon.mobiletesttask.domain.model.UserDetails
import com.ramcosta.composedestinations.annotation.Destination
import kotlin.random.Random

@Destination
@Composable
fun UserDatailItems(
    userDetails: UserDetails,
    modifier: Modifier = Modifier

) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        ) {

            Card(
                modifier = modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .padding(top = 10.dp, end = 10.dp)
                    .border(
                        4.dp, shape = RoundedCornerShape(10.dp),
                        color = Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1f
                        )
                    ),
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp,
            ) {
            Image(
                painter = rememberAsyncImagePainter(userDetails.thumbnail),
                contentDescription ="User image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            }
            Spacer(modifier = Modifier.height(4.dp))

        // text that displays the users name
            Text(
                text = "${userDetails.name.title}. ${userDetails.name.first} ${userDetails.name.last} ",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 20.dp),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Left,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // text that displays the users gender
        TextComponent(text = "Gender: ${userDetails.gender}")
        // text that displays the users email
        TextComponent(text = "Email: ${userDetails.email}")
        // text that displays the phone number
        TextComponent(text = "Phone No: ${userDetails.phone}")
        // text that displays the address
        TextComponent(text = "Address: ${userDetails.location.street.number}, ${userDetails.location.street.name}.")
        // text that displays the city
        TextComponent(text = "City: ${userDetails.location.city}")
        // text that displays the state
        TextComponent(text = "State: ${userDetails.location.state}")
        // text that displays the user Country
        TextComponent(text = "Country: ${userDetails.location.country}")

        Spacer(modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(Color.Gray)
        )
    }
}
