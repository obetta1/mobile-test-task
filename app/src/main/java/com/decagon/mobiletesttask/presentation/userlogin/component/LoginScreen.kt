package com.decagon.mobiletesttask.presentation.userlogin.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.decagon.mobiletesttask.common.validateEmailInput
import com.decagon.mobiletesttask.common.validatePassword
import com.decagon.mobiletesttask.presentation.destinations.UserDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
){
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var emailErrorState by remember {
        mutableStateOf(false)
    }
    var passwordErrorState by remember {
        mutableStateOf(false)
    }
    var passwordErrorText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            modifier = Modifier.padding(bottom = 80.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
        )

        //email text input field
        OutlinedTextField(
            value = emailState,
            onValueChange ={ emailState = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 5.dp),
            shape = RoundedCornerShape(20.dp),
            label = {Text(text = "Enter email")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.LightGray,
                errorBorderColor = Color.Red,
                focusedBorderColor = Color.Green,
            ),
            isError = emailErrorState,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
        if (emailErrorState){
            Text(
                text ="Invalid email format",
                color = Color.Red,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // password text input field
        OutlinedTextField(
                value = passwordState,
                onValueChange ={
                    passwordState = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp),
                shape = RoundedCornerShape(20.dp),
                label = {Text(text = "Enter password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.LightGray,
                    errorLabelColor = Color.Red,
                    errorLeadingIconColor = Color.Red
                ),
                isError = passwordErrorState,
                leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "emailIcon") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        if (passwordErrorState){
            Text(
                text =passwordErrorText,
                color = Color.Red,
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Login button. validate the email and password input when the user clicks on login
        Button(
            onClick = {
                val  validateEmail =validateEmailInput(emailState)
                val validatePassword =validatePassword(passwordState)

                if (
                    validateEmail && validatePassword.isEmpty()
                  ){
                      navigator.navigate(UserDetailsScreenDestination)
                      passwordErrorState =false
                      emailErrorState = false
                  }else{
                      emailErrorState = !validateEmail
                      if (validatePassword(passwordState).isNotEmpty()){
                          passwordErrorState =true
                          passwordErrorText = validatePassword(passwordState)[0]
                      }else{
                          passwordErrorState = false
                      }
                  }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White)
        ) {
            Text(text = "Login")
        }
    }
}
