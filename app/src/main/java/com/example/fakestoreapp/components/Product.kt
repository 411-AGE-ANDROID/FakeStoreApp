package com.example.fakestoreapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.productList
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@Composable
fun ProductItem(product: Product){
    Column(
        modifier = Modifier
            .padding(20.dp)
            .height(200.dp)
            .fillMaxWidth()

    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = product.title,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview()
@Composable
fun ProductItemPreview(){
    FakeStoreAppTheme {
        ProductItem(
            product = productList[0]
        )
    }
}