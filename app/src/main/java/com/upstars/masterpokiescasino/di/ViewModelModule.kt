package com.upstars.masterpokiescasino.di

import androidx.lifecycle.ViewModel
import com.upstars.masterpokiescasino.screens.main.presenation.MainScreenViewModel
import com.upstars.masterpokiescasino.screens.privacyPolicy.presentation.PrivacyPolicyScreenViewModel
import com.upstars.masterpokiescasino.screens.rouletteWheel.presentation.RouletteWheelScreenViewModel
import com.upstars.masterpokiescasino.screens.slots.presentation.SlotsScreenViewModel
import com.upstars.masterpokiescasino.screens.splash.presentation.SplashScreenViewModel
import com.upstars.masterpokiescasino.screens.webview.presentation.WebviewScreenViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun bindSplashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PrivacyPolicyScreenViewModel::class)
    fun bindPrivacyPolicyScreenViewModel(viewModel: PrivacyPolicyScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RouletteWheelScreenViewModel::class)
    fun bindRouletteWheelScreenViewModel(viewModel: RouletteWheelScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SlotsScreenViewModel::class)
    fun bindSlotsScreenViewModel(viewModel: SlotsScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WebviewScreenViewModel::class)
    fun bindWebviewScreenViewModel(viewModel: WebviewScreenViewModel): ViewModel
}

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
