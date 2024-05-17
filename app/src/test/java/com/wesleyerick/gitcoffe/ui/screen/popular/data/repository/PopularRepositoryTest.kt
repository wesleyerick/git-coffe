package com.wesleyerick.gitcoffe.ui.screen.popular.data.repository

import com.wesleyerick.gitcoffe.CoroutinesTestRule
import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesResult
import com.wesleyerick.gitcoffe.ui.screen.popular.data.service.PopularService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Response

class PopularRepositoryTest : KoinTest {

    private companion object {
        const val MOCK_PAGE = 1
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val service: PopularService = mockk()
    private val repository: IRepositoryPopular by inject()


    @Before
    fun setup() {
        startKoin {
            modules(module {
                single { PopularRepository(service) }
            })
        }
    }

    @Test
    fun `test getList success`() = runTest {
        val expectedResponse = Response.success(
            PopularRepositoriesResult()
        )
        coEvery { service.getList(MOCK_PAGE) } returns expectedResponse

        val result = repository.getList(MOCK_PAGE)

        assertEquals(expectedResponse, result)
    }

    @After
    fun tearDown() {
        GlobalContext.stopKoin()
    }
}