package com.example.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fakestoreapp.components.ProductItem
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.productList
import com.example.fakestoreapp.services.ProductsService
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductsScreen(
    innerPadding: PaddingValues = PaddingValues(10.dp),
    navController: NavController = rememberNavController()
){
    val BASE_URL = "https://fakestoreapi.com/"
    var products by remember {
        mutableStateOf(listOf<Product>())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }
    //EFECTOS SECUNDARIOS!!!!
    // ESTA FUNCION SE EJECUTA CADA QUE LA KEY CAMBIA, SI SE PONE ALGO CONSTANTE
    // SOLO SE EJECUTA AL INICIAR LA APP
    LaunchedEffect(key1 = true) {
       // logica de conectarse al API!!!!
        try{
            // CREAR UNA INSTANCIA DE RETROFIT!!! --> LIBRERIA PARA HACER PETICIONES HTTP
            // Patron de Diseño Builder -- Singleton, Builder, Repository, DI, MVVM
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            // EJECUTAR LA PETICION HTTP
            val result = async(Dispatchers.IO) {
                // TENDRIAAMOS QUE HACER MUCHO CODIGO
                val productService = retrofitBuilder.create(ProductsService::class.java)
                productService.getAllProducts()
            }

            // MANEJAR LA RESPUESTA
            Log.i("ProductsScreen",result.await().toString())
            products = result.await()
            isLoading = false
        }
        catch (e: Exception){
            Log.e("ProductsScreen",e.message.toString())
            isLoading = false
        }
    }

    if(isLoading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(10.dp)
        ) {

            items(products){ product ->
                ProductItem(product = product, onClick = {
                    navController.navigate("products/${product.id}")
                })
            }
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