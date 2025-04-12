package com.hamidhosen.easyshop.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hamidhosen.easyshop.AppUtil
import com.hamidhosen.easyshop.R
import com.hamidhosen.easyshop.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome back!",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            "Sign in to your account",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.login_banner),
            contentDescription = "Login Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {
            Text("Email address")
        }, modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                when {
                    email.isBlank() -> {
                        AppUtil.showTest(context, "Email is required")
                    }

                    password.isBlank() -> {
                        AppUtil.showTest(context, "Password is required")
                    }

                    else -> {
                        isLoading = true
                        authViewModel.login(email, password) { success, errorMessage ->
                            isLoading = false
                            if (success) {
                                navController.navigate("home") {
                                    popUpTo("auth") {
                                        inclusive = true
                                    }
                                }
                            } else {
                                isLoading = false
                                AppUtil.showTest(context, errorMessage ?: "Something went wrong")
                            }
                        }
                    }
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(if (isLoading) "Login in" else "Login", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Don't have an account? Sign up", modifier = Modifier.clickable {
                navController.navigate("signup")
            }, color = Color.Blue, fontSize = 14.sp, fontFamily = FontFamily.Serif
        )
    }
}