package com.hamidhosen.easyshop.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CategoryProductPage(modifier: Modifier = Modifier, categoryId: String) {
    Text("Category Product page :::::"+categoryId)
}