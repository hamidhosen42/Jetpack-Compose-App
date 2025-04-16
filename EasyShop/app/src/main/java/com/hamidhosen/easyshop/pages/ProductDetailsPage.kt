package com.hamidhosen.easyshop.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.hamidhosen.easyshop.model.ProductModel
import androidx.compose.ui.text.font.FontWeight

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

    Spacer(
        modifier = Modifier.height(20.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        product?.let { prod ->
            Text(
                prod.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            val pagerState = rememberPagerState { prod.images.size }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalPager(
                    state = pagerState,
                    pageSpacing = 24.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) { page ->
                    AsyncImage(
                        model = prod.images[page],
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                DotsIndicator(
                    totalDots = prod.images.size,
                    selectedIndex = pagerState.currentPage
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Price: ৳${prod.price}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Add to Favorite"
                    )
                }
            }

            if (prod.otherDetails.isNotEmpty())
                Text(
                    text = "Other Product Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

            Spacer(modifier = Modifier.height(8.dp))

            prod.otherDetails.forEach { (key, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text("$key :", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Text("$value", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Add to Cart", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == selectedIndex) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex) Color.Black else Color.LightGray)
            )
        }
    }
}
