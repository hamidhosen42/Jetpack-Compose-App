package com.hamidhosen.easyshop.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.hamidhosen.easyshop.components.ProductItemView
import com.hamidhosen.easyshop.model.ProductModel

@Composable
fun CategoryProductPage(modifier: Modifier = Modifier, categoryId: String) {
    var productList by remember { mutableStateOf<List<ProductModel>>(emptyList()) }

    LaunchedEffect(categoryId) {
        Firebase.firestore.collection("data").document("stock")
            .collection("products")
            .whereEqualTo("category", categoryId)
            .get()
            .addOnSuccessListener { result ->
                val resultList = result.documents.mapNotNull { doc ->
                    doc.toObject(ProductModel::class.java)
                }
                productList = resultList
            }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(productList.chunked(2)) { rowItem ->
            Row {
                rowItem.forEach {
                    ProductItemView(product = it, modifier = Modifier.weight(1f))
                }
                if (rowItem.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}