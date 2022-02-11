package com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PanoramaFishEye
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenEvent
import com.BFCAI.encryptionapp.Presentation.LoginScreen.LoginScreenState
import com.BFCAI.encryptionapp.android.R

@Composable
fun TextFieldComponent(
    onTriggerEvent: (LoginScreenEvent) -> Unit,
    loginSuccess:()-> Unit,
    signUpClick:()->Unit,
    ) {
    var emailtext by remember { mutableStateOf(TextFieldValue("")) }
    var passwordtext by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var iserror by remember { mutableStateOf(false) }
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) { append("Don't have an account? ") }
        pushStringAnnotation(tag = "SignUp", annotation = "SignUp")
        withStyle(style = SpanStyle(color = Color.Yellow)) { append("Sign Up") }
        // when pop is called it means the end of annotation with current tag
        pop()
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 60.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = emailtext,
                onValueChange = {
                    emailtext = it
                    iserror = false
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(text = "Email", color = Color.Gray) },
                shape = MaterialTheme.shapes.large,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    focusedIndicatorColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,
                    textColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary

                    )
                },
                singleLine = true,
                isError = iserror

                )

        }
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = passwordtext,
                onValueChange = {
                    passwordtext = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(text = "Password", color = Color.Gray) },
                shape = MaterialTheme.shapes.large,
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
                    } else {
                        onTriggerEvent(LoginScreenEvent.login(emailtext.text, passwordtext.text , loginSuccess))
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                modifier = Modifier.background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.medium
                )
            ) {
                Text(
                    text = "Login",
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
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)

        ) {
            ClickableText(
                text = annotatedText,
                onClick = {
                    signUpClick()
                }
            )
        }
    }
}