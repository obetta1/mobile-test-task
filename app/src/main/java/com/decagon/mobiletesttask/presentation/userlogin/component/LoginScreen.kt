package com.decagon.mobiletesttask.presentation.userlogin.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    var showPassword by remember {
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
                .padding(top = 10.dp, bottom = 5.dp)
                .semantics { contentDescription = "email textfield" },
            shape = RoundedCornerShape(15.dp),
            label = {Text(text = "Enter email")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF0EBEA),
                errorBorderColor = Color.Red,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.White
            ),
            isError = emailErrorState,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
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
                    .padding(top = 10.dp, bottom = 5.dp)
                    .semantics { contentDescription = "password textfield" },
                shape = RoundedCornerShape(15.dp),
                label = {Text(text = "Enter password")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xFFF0EBEA),
                    errorLabelColor = Color.Red,
                    errorLeadingIconColor = Color.Red,
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.White
                ),
                isError = passwordErrorState,
                leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "emailIcon") },

            visualTransformation =
            if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val passwordIcon = if (showPassword)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // This is used to localized description for accessibility services
                val description = if (showPassword) "Hide password" else "Show password"

                // This is used to toggle button to hide or display password
                IconButton(onClick = {showPassword = !showPassword}){
                    Icon(imageVector  = passwordIcon, description)
                }
            },
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
                      if (validatePassword.isNotEmpty()){
                          passwordErrorState =true
                          passwordErrorText = validatePassword[0]
                      }else{
                          passwordErrorState = false
                      }
                  }
            },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Login button" },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White)
        ) {
            Text(text = "Login")
        }
    }
}
