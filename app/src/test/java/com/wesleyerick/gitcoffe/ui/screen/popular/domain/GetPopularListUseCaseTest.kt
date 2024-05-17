//package com.wesleyerick.gitcoffe.ui.screen.popular.domain
//
//import androidx.compose.ui.semantics.error
//import androidx.preference.isNotEmpty
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runTest
//import org.junit.After
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//@ExperimentalCoroutinesApi
//class GetPopularListUseCaseTest {
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var useCase: GetPopularListUseCase
//    private val repository = mockk<IRepositoryPopular>()
//
//    @Before
//    fun setUp() {
//        useCase = GetPopularListUseCase(repository)
//    }
//
//    @Test
//    fun `test get popular list success`() = runTest {
//        // Given
//        coEvery { repository.getList() } returns Result.Success(listOf(Item()))
//
//        // When
//        val result = useCase()
//
//        // Then
//        assertThat(result).isInstanceOf(Result.Success::class.java)
//        (result as Result.Success).data.let { list ->
//            assertThat(list).isNotEmpty()
//        }
//    }
//
//    @Test
//    fun `test get popular list failure`() = runTest {
//        // Given
//        coEvery { repository.getList() } returns Result.Failure(Exception("error"))
//
//        // When
//        val result = useCase()
//
//        // Then
//        assertThat(result).isInstanceOf(Result.Failure::class.java)
//        (result as Result.Failure).error.let { error ->
//            assertThat(error).hasMessageThat().isEqualTo("error")
//        }
//    }
//}