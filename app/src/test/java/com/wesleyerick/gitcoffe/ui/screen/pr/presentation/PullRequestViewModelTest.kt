package com.wesleyerick.gitcoffe.ui.screen.pr.presentation

import androidx.lifecycle.Observer
import com.wesleyerick.gitcoffe.CoroutinesTestRule
import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesItem
import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItemResult
import com.wesleyerick.gitcoffe.ui.screen.pr.domain.GetPullRequestListUseCase
import com.wesleyerick.gitcoffe.utils.ResourceView
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class PullRequestViewModelTest : KoinTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val useCase: GetPullRequestListUseCase = mockk()
    private val viewModel: PullRequestViewModel by inject()

    private val mockObserver: Observer<ResourceView<List<PullRequestItemResult>>> =
        mockk(relaxed = true)


    @BeforeEach
    fun setUp() {
        val myModule = module {
            single { useCase }
            viewModel { PullRequestViewModel(get()) }
        }
        startKoin { modules(myModule) }
        viewModel.state.observeForever(mockObserver)
    }

    @Test
    fun `getList should post success when use case returns data`() = runTest {
        viewModel.getList(creator = "Wesley", repo = "git-coffe")

        verify { mockObserver.onChanged(ResourceView.Success(listOf(PullRequestItemResult()))) }
        assertEquals(
            listOf(ResourceView.Success(listOf(PopularRepositoriesItem()))),
            viewModel.state.value
        )
    }

    @Test
    fun `getList should post error when use case returns error`() = runTest {
        viewModel.getList(creator = "Wesley", repo = "git-coffe")

        val errorMessage = "Error message"

        verify { mockObserver.onChanged(ResourceView.Error(errorMessage)) }
        assertEquals(
            listOf(ResourceView.Error(errorMessage)),
            viewModel.state.value
        )
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }
}