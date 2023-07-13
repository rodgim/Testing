package com.rodgim.testing.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rodgim.testing.getOrAwaitValue
import com.rodgim.testing.other.Constants
import com.rodgim.testing.other.Status
import com.rodgim.testing.repositories.FakeShoppingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShoppingViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insert shopping item with empty field, returns error`() = runTest {
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long name, returns error`() = runTest {
        val string = buildString {
            for (i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem(string, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too long price, returns error`() = runTest {
        val string = buildString {
            for (i in 1..Constants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertShoppingItem("name", "5", string)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with too high amount, returns error`() = runTest {
        viewModel.insertShoppingItem("name", "99999999999999999999999", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input, returns success`() = runTest {
        viewModel.insertShoppingItem("name", "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}