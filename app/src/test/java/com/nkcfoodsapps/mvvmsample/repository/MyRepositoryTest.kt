package com.nkcfoodsapps.mvvmsample.repository

import com.nkcfoodsapps.mvvmsample.model.data.Employees
import com.nkcfoodsapps.mvvmsample.utils.Resource
import com.nkcfoodsapps.mvvmsample.viewmodel.DataViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Date
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
class MyRepositoryTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

//    @get:Rule(order = 1)
//    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Inject
    lateinit var repository: UserRepository

    private lateinit var viewModel: DataViewModel

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
        Dispatchers.setMain(testDispatcher)

//        val composeView = composeTestRule.activity.findViewById<ComposeView>()
        /* composeTestRule.setContent {
             viewModel = hiltViewModel()
         }*/
        viewModel = DataViewModel(repository)


    }

    @Test
    fun fetchData_success() {
        Assert.assertEquals("Test execution", repository.getName())
        Assert.assertEquals("Test execution", viewModel.getName())
    }

    @Test
    fun get_employees_test_repository() = runTest {

        val emissions = mutableListOf<Resource<Employees>>()
        repository.getEmployees().collect {
            println("TIME: ${Date()}: FLOW:STATUS: ${it.status}")
            emissions.add(it)
        }

        advanceUntilIdle()


        assertEquals(2, emissions.size)
        assertEquals(Resource.Status.LOADING, emissions[0].status)
        assertEquals(Resource.Status.SUCCESS, emissions[1].status)
//        assertEquals(Resource.Status.SUCCESS, emissions[2].status)
    }

    @Test
    fun get_employees_test_viewmodel() = runTest {

        val emissions = mutableListOf<Resource<Employees>>()

        viewModel.employees.observeForever {
            println("LIVE:STATUS: ${it.status}")
            emissions.add(it)
        }
        viewModel.getEmployees()
        advanceUntilIdle()

        assertEquals(2, emissions.size)
        assertEquals(Resource.Status.LOADING, emissions[0].status)
        assertEquals(Resource.Status.SUCCESS, emissions[1].status)
//        assertEquals(Resource.Status.SUCCESS, emissions[2].status)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }


}