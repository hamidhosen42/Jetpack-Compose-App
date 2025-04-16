package com.hamidhosen.easyshop.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.hamidhosen.easyshop.GlobalNavigation
import com.hamidhosen.easyshop.model.CategoryModel

@Composable
fun CategoriesView(modifier: Modifier = Modifier) {
    var categoryList by remember { mutableStateOf<List<CategoryModel>>(emptyList()) }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data").document("stock")
            .collection("categories")
            .get()
            .addOnSuccessListener { result ->
                val categories = result.documents.mapNotNull { doc ->
                    doc.toObject(CategoryModel::class.java)
                }
                categoryList = categories
            }
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(categoryList) { item ->
            CategoryItem(category = item)
        }
    }
}

@Composable
fun CategoryItem(category: CategoryModel) {
    Card(
        modifier = Modifier.size(100.dp).
        clickable{
            GlobalNavigation.navController.navigate("category-products/${category.id}")
        },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier
                    .size(64.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = category.name,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}