package com.example.fakestoreapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.services.ProductsService
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductDetailScreen(id:Int){
    val BASE_URL = "https://fakestoreapi.com/"
    var product by remember {
        mutableStateOf<Product?>(null)
    }
    LaunchedEffect(key1 = true) {
        try{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val result = async(Dispatchers.IO) {
                val productService = retrofit.create(ProductsService::class.java)
                productService.getProductById(id)
            }
            product = result.await()
        }
        catch (e: Exception){

        }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = product?.title ?: "Producto no encontrado")
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ProductDetailScreenPreview(){
    FakeStoreAppTheme {
        ProductDetailScreen(
            id = 1
        )
    }
}