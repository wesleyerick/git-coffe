import androidx.lifecycle.Observer
import com.wesleyerick.gitcoffe.CoroutinesTestRule
import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesItem
import com.wesleyerick.gitcoffe.ui.screen.popular.domain.GetPopularListUseCase
import com.wesleyerick.gitcoffe.ui.screen.popular.presentation.PopularViewModel
import com.wesleyerick.gitcoffe.utils.ResourceView
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest

@ExperimentalCoroutinesApi
class PopularViewModelTest : AutoCloseKoinTest() {


    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    private val useCase: GetPopularListUseCase = mockk()
    private val viewModel: PopularViewModel by inject()

    private val mockObserver: Observer<ResourceView<List<PopularRepositoriesItem>>> =
        mockk(relaxed = true)


    @BeforeEach
    fun setUp() {
        val myModule = module {
            single { useCase }
            viewModel { PopularViewModel(get()) }
        }
        startKoin { modules(myModule) }
        viewModel.state.observeForever(mockObserver)
    }

    @Test
    fun `getList should post success when use case returns data`() = runTest {
        viewModel.getList()

        verify { mockObserver.onChanged(ResourceView.Success(listOf(PopularRepositoriesItem()))) }
        Assertions.assertEquals(
            listOf(ResourceView.Success(listOf(PopularRepositoriesItem()))),
            viewModel.state.value
        )
    }

    @Test
    fun `getList should post error when use case returns error`() = runTest {
        viewModel.getList()

        val errorMessage = "Error message"

        verify { mockObserver.onChanged(ResourceView.Error(errorMessage)) }
        Assertions.assertEquals(
            listOf(ResourceView.Error(errorMessage)),
            viewModel.state.value
        )
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
    }
}