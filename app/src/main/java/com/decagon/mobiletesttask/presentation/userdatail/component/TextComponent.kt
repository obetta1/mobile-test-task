package com.decagon.mobiletesttask.presentation.userdatail.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp


@Composable
fun TextComponent(text:String){
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        fontStyle = FontStyle.Normal
    )
    Spacer(modifier = Modifier.height(4.dp))
}