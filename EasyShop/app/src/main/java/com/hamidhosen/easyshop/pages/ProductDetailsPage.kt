package com.hamidhosen.easyshop.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.hamidhosen.easyshop.model.ProductModel

@Composable
fun ProductDetailsPage(modifier: Modifier = Modifier, productId: String) {
    var product by remember { mutableStateOf<ProductModel?>(null) }

    LaunchedEffect(productId) {
        Firebase.firestore.collection("data").document("stock")
            .collection("products")
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    product = document.toObject(ProductModel::class.java)
                }
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        product?.let {
            Text(
                it.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Column {
                val pagerState = rememberPagerState (0){
                    product!!.images.size
                }
                HorizontalPager(
                    state = pagerState,
                    pageSpacing = 24.dp
                ) {
                    AsyncImage(
                        model = product!!.images.get(it),
                        contentDescription = "Product Images",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                DotsIndicator(
                    totalDots = product!!.images.size,
                    selectedIndex = pagerState.currentPage
                )
            }
        }
    }
}

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int) {
    TODO("Not yet implemented")
}