package com.upstars.masterpokiescasino.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.upstars.masterpokiescasino.core.viewmodel.ViewModelFactory
import com.upstars.masterpokiescasino.screens.main.presenation.MainScreen
import com.upstars.masterpokiescasino.screens.privacyPolicy.presentation.PrivacyPolicyScreen
import com.upstars.masterpokiescasino.screens.result.presentation.PrizeType
import com.upstars.masterpokiescasino.screens.result.presentation.ResultScreen
import com.upstars.masterpokiescasino.screens.rouletteWheel.presentation.RouletteWheelScreen
import com.upstars.masterpokiescasino.screens.slots.presentation.SlotsScreen
import com.upstars.masterpokiescasino.screens.splash.presentation.SplashScreen
import com.upstars.masterpokiescasino.screens.webview.presentation.WebviewScreen

@Composable
@Suppress("FunctionNaming", "LongMethod")
fun Navigation(
    viewModelFactory: ViewModelFactory,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SPLASH.route
    ) {
        composable(NavigationRoute.SPLASH.route) {
            SplashScreen(
                viewModel = viewModel(factory = viewModelFactory),
                navigateToMainScreen = {
                    navController.popBackStack()
                    navController.navigate(NavigationRoute.MAIN.route)
                },
                navigateToWebviewScreen = {
                    navController.popBackStack()
                    navController.navigate(NavigationRoute.WEBVIEW.route)
                }
            )
        }
        composable(NavigationRoute.MAIN.route) {
            MainScreen(
                viewModel = viewModel(factory = viewModelFactory),
                navigateToSlots = {
                    navController.navigate(NavigationRoute.SLOTS.route)
                },
                navigateToRouletteWheel = {
                    navController.navigate(NavigationRoute.ROULETTE_WHEEL.route)
                },
                navigateToPrivacyPolicyScreen = {
                    navController.navigate(NavigationRoute.PRIVACY_POLICY.route)
                }
            )
        }
        composable(NavigationRoute.PRIVACY_POLICY.route) {
            PrivacyPolicyScreen(
                privacyPolicyScreenViewModel = viewModel(factory = viewModelFactory),
                onBack = navController::popBackStack
            )
        }
        composable(NavigationRoute.SLOTS.route) {
            SlotsScreen(
                viewModel = viewModel(factory = viewModelFactory),
                navigateToResultScreen = {
                    navController.navigate(
                        NavigationRoute.RESULT.route.replace(
                            "{$COUNT_ARGUMENT_KEY}",
                            it.toString()
                        ).replace(
                            "{$PRIZE_TYPE_ARGUMENT_KEY}",
                            PrizeType.COINS.ordinal.toString()
                        )
                    )
                }
            )
        }
        composable(NavigationRoute.ROULETTE_WHEEL.route) {
            RouletteWheelScreen(
                viewModel = viewModel(factory = viewModelFactory),
                navigateToResultScreen = {
                    navController.navigate(
                        NavigationRoute.RESULT.route.replace(
                            "{$COUNT_ARGUMENT_KEY}",
                            it.toString()
                        ).replace(
                            "{$PRIZE_TYPE_ARGUMENT_KEY}",
                            PrizeType.GOLD_BARS.ordinal.toString()
                        )
                    )
                }
            )
        }
        composable(
            route = NavigationRoute.RESULT.route,
            arguments = listOf(
                navArgument(COUNT_ARGUMENT_KEY) { type = NavType.IntType },
                navArgument(PRIZE_TYPE_ARGUMENT_KEY) { type = NavType.IntType }
            )
        ) {
            ResultScreen(
                count = it.arguments?.getInt(COUNT_ARGUMENT_KEY)
                    ?: error("Count should be provided for result screen!"),
                prizeType = PrizeType.entries[it.arguments?.getInt(PRIZE_TYPE_ARGUMENT_KEY)
                    ?: error("Prize type should be provided for result screen!")],
                onBack = navController::popBackStack,
                navigateToMainScreen = {
                    navController.popBackStack(
                        route = NavigationRoute.MAIN.route,
                        inclusive = false
                    )
                }
            )
        }
        composable(NavigationRoute.WEBVIEW.route) {
            WebviewScreen(viewModel = viewModel(factory = viewModelFactory))
        }
    }
}
