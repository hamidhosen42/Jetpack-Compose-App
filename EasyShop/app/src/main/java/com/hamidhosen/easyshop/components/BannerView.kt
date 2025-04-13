package com.hamidhosen.easyshop.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun BannerView(modifier: Modifier = Modifier) {
    var bannerList by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("banners")
            .get().addOnCompleteListener() {
                bannerList = it.result.get("banners") as List<String>
            }
    }

    Text(bannerList.toString())
}