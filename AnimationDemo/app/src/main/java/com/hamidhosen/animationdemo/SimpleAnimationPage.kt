package com.hamidhosen.animationdemo

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SimpleAnimation(modifier: Modifier = Modifier) {

    var scale = remember {
        Animatable(initialValue = 0f)
    }

    var animateAgain by remember {
        mutableStateOf(false)
    }

    // Using LaunchedEffect to react to animateAgain state change
    LaunchedEffect(key1 = animateAgain) {
        scale.animateTo(
            targetValue = if (animateAgain) 360f else 0f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = { OvershootInterpolator(2f).getInterpolation(it) }
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan) // Set your background color here
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .rotate(scale.value),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            Button(
                onClick = {
                    animateAgain = !animateAgain // Toggle the animation state
                }
            ) {
                Text("Animate")
            }
        }
    }
}