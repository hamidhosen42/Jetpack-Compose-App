package com.hamidhosen.listdemoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListDemo(){
    LazyColumnDemo()
}

@Composable
fun LazyColumnDemo(){
    var myList = getAllMarvelChars()
    LazyColumn ( content = {
        itemsIndexed(myList, itemContent = { index,item->
            MarvelItem(item=item)
        })
    } )
}

@Composable
fun  MarvelItem(item:MarvelChar){
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.clip(CircleShape).size(64.dp).scale(1.0F)
            )
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(item.charName, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ))

            Text(item.name, style = TextStyle(
                fontSize = 18.sp,
            ))
        }
    }
}

//@Composable
//fun LazyColumnDemo(){
//    LazyColumn ( content = {
//        items(100, itemContent = {
//            TextItem("Index $it")
//        })
//    } )
//}

@Composable
fun SimpleColumn() {

    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for ( i in 1..500){
            TextItem("Item $i")
        }
    }
}

@Composable
fun TextItem(text: String) {
    Text(text,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}