package com.hamidhosen.easyshop.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamidhosen.easyshop.components.BannerView
import com.hamidhosen.easyshop.components.CategoriesView
import com.hamidhosen.easyshop.components.HeaderView

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderView(modifier)
        BannerView(modifier = Modifier.height(160.dp))
        Text(
            "Categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        CategoriesView(modifier)
    }
}