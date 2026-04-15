package com.example.fakestoreapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fakestoreapp.components.ProductItem
import com.example.fakestoreapp.models.productList
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@Composable
fun ProductsScreen(
    innerPadding: PaddingValues = PaddingValues(10.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(10.dp)
    ) {

        items(productList){ product ->
            ProductItem(product = product)
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ProductsScreenPreview(){
    FakeStoreAppTheme {
        ProductsScreen()
    }
}