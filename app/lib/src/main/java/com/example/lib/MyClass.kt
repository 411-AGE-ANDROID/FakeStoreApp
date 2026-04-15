package com.example.lib

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    println("=== CORRUTINAS ===")

    // 1. RunBlocking, es una corrutina que BLOQUEA EL HILO PRINCIPAL
    // NO SE DE USAR EN PRODUCCION!!!
    //corrutinaLaunch()
    corrutinaAsync()
}

fun corrutinaAsync(){
    runBlocking {
        println("Haciendo peticion /GET")
        val result = async {
            println("Haciendo consulta a la API")
            delay(6000)
            println("Retornando resultado")
            """ { "id":1,"name":"Juan" } """
        }
        println("El resultado de la peticion es ${result.await()}")
    }
}

fun corrutinaLaunch(){
    runBlocking {
        println("Cargando interfaz grafica...")
        launch(Dispatchers.IO){
            consultaAPI()
        }
        println("La UI sigue cargando mientras termina la corruina")
    }
}


suspend fun consultaAPI(){
    println("Consultando la API")
    delay(6000)
    println("La respuesta es que todo salio bien")
}