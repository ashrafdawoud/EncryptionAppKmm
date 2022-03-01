package com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.Presentation.SignUpScreen.SignupScreenEvent
import com.BFCAI.encryptionapp.android.R

@Composable
fun FormComponents(
    onTriggerEvent:(SignupScreenEvent)->Unit,
    signupSuccess:()->Unit
) {
    var emailtext by remember { mutableStateOf(TextFieldValue("")) }
    var passwordtext by remember { mutableStateOf(TextFieldValue("")) }
    var re_passwordtext by remember { mutableStateOf(TextFieldValue("")) }
    var Usernametext by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var re_passwordVisibility by remember { mutableStateOf(false) }
    var iserror by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 40.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = Usernametext,
                onValueChange = {
                    Usernametext = it
                },
                placeholder = { Text(text = "UserName", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_account),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,
                    textColor = Color.White
                ),
                shape = MaterialTheme.shapes.large,
                isError = iserror
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = emailtext,
                onValueChange = {
                    emailtext = it
                },
                placeholder = { Text(text = "Email", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,
                    textColor = Color.White
                ),
                shape = MaterialTheme.shapes.large,
                isError = iserror
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = passwordtext,
                onValueChange = {
                    passwordtext = it
                },
                placeholder = { Text(text = "Password", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,
                    textColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                singleLine = true,
                visualTransformation = if (re_passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        re_passwordVisibility = !re_passwordVisibility
                    }) {
                        Icon(
                            imageVector = Icons.Default.RemoveRedEye,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                isError = iserror
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = re_passwordtext,
                onValueChange = {
                    re_passwordtext = it
                },
                placeholder = { Text(text = "Re-Password", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,
                    textColor = Color.White
                ),
                shape = MaterialTheme.shapes.large,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                },
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            imageVector = Icons.Default.RemoveRedEye,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                isError = iserror
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 40.dp)

        ) {
            Button(
                onClick = {
                    if (emailtext.text.equals("")) {
                        iserror = true
                    } else if (passwordtext.text .equals( "")) {
                        iserror = true
                    }else if (re_passwordtext.text .equals( "")) {
                        iserror = true
                    }else if (Usernametext.text .equals( "")) {
                        iserror = true
                    }else if(!passwordtext.text .equals(re_passwordtext.text )) {
                        iserror = true
                    } else {
                        onTriggerEvent(SignupScreenEvent.signup(emailtext.text,passwordtext.text,Usernametext.text,signupSuccess))
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                modifier = Modifier.background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.large
                ),

            ) {
                Text(
                    text = "SignUp",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        start = 40.dp,
                        end = 40.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )

                )
            }
        }
    }
}