package com.wesleyerick.gitcoffe.ui.screen.popular.domain

import com.wesleyerick.gitcoffe.CoroutinesTestRule
import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesResult
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.PopularRepository
import com.wesleyerick.gitcoffe.utils.Result
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class GetStarshipsUseCaseTest {

    private companion object {
        const val MOCK_EXCEPTION = "Error mockk"
        const val MOCK_EXACTLY = 1
        const val MOCK_PAGE = 1
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private lateinit var useCase: GetPopularListUseCase
    private val repository: PopularRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = GetPopularListUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when execute api getList return mock success`() = runTest {
        coEvery { repository.getList(MOCK_PAGE) } returns Response
            .success(
                PopularRepositoriesResult()
            )

        useCase(MOCK_PAGE)

        coVerify(exactly = MOCK_EXACTLY) { Result.Success<Any>(any()) }
        verify { Result.Failure(any()) wasNot called }
    }

    @Test
    fun `when execute api getList return mock error`() = runTest {
        coEvery { repository.getList(MOCK_PAGE) } throws Exception(MOCK_EXCEPTION)

        useCase(MOCK_PAGE)

        coVerify(exactly = MOCK_EXACTLY) { Result.Failure(any()) }
        verify { Result.Success<Any>(any()) wasNot called }
    }
}