package com.wesleyerick.gitcoffe.ui.screen.pr.domain

import com.wesleyerick.gitcoffe.CoroutinesTestRule
import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItemResult
import com.wesleyerick.gitcoffe.ui.screen.pr.data.repository.PullRequestRepository
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

class GetPullRequestListUseCaseTest {

    private companion object {
        const val MOCK_EXCEPTION = "Error mockk"
        const val MOCK_EXACTLY = 1
        const val MOCK_CREATOR = "Wes"
        const val MOCK_REPO = "git-coffe"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private lateinit var useCase: GetPullRequestListUseCase
    private val repository: PullRequestRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        useCase = GetPullRequestListUseCase(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when execute api getList return mock success`() = runTest {
        coEvery { repository.getList(MOCK_CREATOR, MOCK_REPO) } returns Response
            .success(
                listOf(
                    PullRequestItemResult(),
                    PullRequestItemResult(),
                )
            )

        useCase(MOCK_CREATOR, MOCK_REPO)

        coVerify(exactly = MOCK_EXACTLY) { Result.Success<Any>(any()) }
        verify { Result.Failure(any()) wasNot called }
    }

    @Test
    fun `when execute api getList return mock error`() = runTest {
        coEvery { repository.getList(MOCK_CREATOR, MOCK_REPO) } throws Exception(MOCK_EXCEPTION)

        useCase(MOCK_CREATOR, MOCK_REPO)

        coVerify(exactly = MOCK_EXACTLY) { Result.Failure(any()) }
        verify { Result.Success<Any>(any()) wasNot called }
    }
}