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
import androidx.compose.runtime.*
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
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {

    var name by remember {
        mutableStateOf("")
    }

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
            "Hello there!",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            "Create an account",
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

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Full name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email address")
            },
            modifier = Modifier.fillMaxWidth()
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
                    name.isBlank() -> {
                        AppUtil.showTest(context, "Full name is required")
                    }

                    email.isBlank() -> {
                        AppUtil.showTest(context, "Email is required")
                    }

                    password.isBlank() -> {
                        AppUtil.showTest(context, "Password is required")
                    }

                    else -> {
                        isLoading = true
                        authViewModel.signup(name, email, password) { success, errorMessage ->
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
            Text(if(isLoading)"Creating" else "Signup", fontSize = 20.sp)
        }


        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Already have an account? Login",
            modifier = Modifier
                .clickable {
                    navController.navigate("login")
                },
            color = Color.Blue,
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif
        )
    }
}