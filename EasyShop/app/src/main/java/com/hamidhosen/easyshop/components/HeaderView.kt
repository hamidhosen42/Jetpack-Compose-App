package com.hamidhosen.easyshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Composable
fun HeaderView(modifier: Modifier = Modifier) {

    var name by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("users")
            .document(Firebase.auth.currentUser!!.uid)
            .get().addOnCompleteListener() {
                name = it.result.get("name").toString()
            }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Welcome back")

            Text(
                if (name.isEmpty()) "Loading..." else name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            onClick = {

            }
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}