package com.decagon.mobiletesttask.presentation.userdatail.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.decagon.mobiletesttask.presentation.UserViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun UserDetailsScreen(
    viewModel: UserViewModel = hiltViewModel()
){
    var searchState by remember {
        mutableStateOf("")
    }
    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState =scaffoldState
    ) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text(
            text = "Search",
            modifier = Modifier.padding(top = 40.dp),
            style = TextStyle(color = Color.Black, fontSize = 28.sp),
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )

        OutlinedTextField(
            value = searchState,
            onValueChange = {
                searchState = it

            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Search") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF0EBEA),
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "emailIcon"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(
                // this is used to filter the list of user
                viewModel.filterList(searchState)
            ) { _, item ->
                UserDatailItems(
                    userDetails = item
                )
            }
        }
    }
    }
}
