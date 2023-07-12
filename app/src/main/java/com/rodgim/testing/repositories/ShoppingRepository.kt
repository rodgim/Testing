package com.rodgim.testing.repositories

import androidx.lifecycle.LiveData
import com.rodgim.testing.data.local.ShoppingItem
import com.rodgim.testing.data.remote.models.ImageResponse
import com.rodgim.testing.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}