package com.softvision.alexlepadatu.typicode.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.whenever
import com.softvision.alexlepadatu.typicode.R
import com.softvision.alexlepadatu.typicode.di.UseCaseModule
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import com.softvision.alexlepadatu.typicode.domain.useCase.albumsList.AlbumsUseCase
import com.softvision.alexlepadatu.typicode.presentation.fragment.AlbumsListFragment
import com.softvision.alexlepadatu.typicode.util.launchFragmentInHiltContainer
import com.softvision.alexlepadatu.typicode.util.listAlbums
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.flowOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@UninstallModules(UseCaseModule::class)
class AlbumsListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val mockUsecase: AlbumsUseCase = mock(AlbumsUseCase::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    inner class MockUseCase {
        @Provides
        fun provideUseCase(): AlbumsUseCase = mockUsecase
    }

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLoadingViewVisibilityWhenLoading() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Loading()))

        prepareFragment()

        onView(withId(R.id.viewLoading)).check(matches(isDisplayed()))
    }

    @Test
    fun testErrorViewVisibilityWhenLoading() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Loading()))

        prepareFragment()

        onView(withId(R.id.viewError)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testNoDataViewVisibilityWhenLoading() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Loading()))

        prepareFragment()

        onView(withId(R.id.viewNoData)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testLoadingViewVisibilityWhenError() {
        val exception = Exception("Error!")
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Error(exception)))

        prepareFragment()

        onView(withId(R.id.viewLoading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testErrorViewVisibilityWhenError() {
        val exception = Exception("Error!")
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Error(exception)))

        prepareFragment()

        onView(withId(R.id.viewError)).check(matches(isDisplayed()))
    }

    @Test
    fun testNoDataViewVisibilityWhenError() {
        val exception = Exception("Error!")
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Error(exception)))

        prepareFragment()

        onView(withId(R.id.viewNoData)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testLoadingViewVisibilityWhenSuccess() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(listAlbums())))

        prepareFragment()

        onView(withId(R.id.viewLoading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testErrorViewVisibilityWhenSuccess() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(listAlbums())))

        prepareFragment()

        onView(withId(R.id.viewError)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testNoDataViewVisibilityWhenSuccess() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(listAlbums())))

        prepareFragment()

        onView(withId(R.id.viewNoData)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testNoDataViewVisibilityWhenSuccessAndEmptyData() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(listOf())))

        prepareFragment()

        onView(withId(R.id.viewNoData)).check(matches(isDisplayed()))
    }

    private fun prepareFragment() {
        launchFragmentInHiltContainer<AlbumsListFragment>(themeResId = R.style.Theme_MaterialComponents_DayNight_DarkActionBar)
    }
}
