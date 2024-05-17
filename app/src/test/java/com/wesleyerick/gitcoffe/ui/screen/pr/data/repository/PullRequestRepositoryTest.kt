package com.wesleyerick.gitcoffe.ui.screen.pr.data.repository

import com.wesleyerick.gitcoffe.CoroutinesTestRule
import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItemResult
import com.wesleyerick.gitcoffe.ui.screen.pr.data.service.PullRequestService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Response

class PullRequestRepositoryTest : KoinTest {

    private companion object {
        const val MOCK_CREATOR = "Wes"
        const val MOCK_REPO = "git-coffe"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val service: PullRequestService = mockk()
    private val repository: IRepositoryPullRequest by inject()


    @Before
    fun setup() {
        startKoin {
            modules(module {
                single { PullRequestRepository(service) }
            })
        }
    }

    @Test
    fun `test getList success`() = runTest {
        val expectedResponse = Response.success(
            listOf(PullRequestItemResult())
        )
        coEvery { service.getList(MOCK_CREATOR, MOCK_REPO) } returns expectedResponse

        val result = repository.getList(MOCK_CREATOR, MOCK_REPO)

        assertEquals(expectedResponse, result)
    }

    @After
    fun tearDown() {
        GlobalContext.stopKoin()
    }
}