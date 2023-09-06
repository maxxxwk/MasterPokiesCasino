package com.upstars.masterpokiescasino

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.upstars.masterpokiescasino.core.viewmodel.ViewModelFactory
import com.upstars.masterpokiescasino.navigation.Navigation
import com.upstars.masterpokiescasino.navigation.NavigationRoute
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var doubleBackToExitPressedOnce = false
    private var isDoubleBackClickExitEnabled = false

    private val mainHandler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        setContent {
            val requestPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = {}
            )
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.simple_screen_background),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                LaunchedEffect(currentBackStackEntry) {
                    isDoubleBackClickExitEnabled =
                        currentBackStackEntry?.destination?.route == NavigationRoute.MAIN.route
                }

                Navigation(
                    viewModelFactory = viewModelFactory,
                    navController = navController
                )
            }
        }
    }

    override fun onBackPressed() {
        if (isDoubleBackClickExitEnabled) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.exit_toast), Toast.LENGTH_SHORT).show()
            mainHandler.postDelayed(
                {
                    doubleBackToExitPressedOnce = false
                },
                DOUBLE_BACK_CLICK_TIME
            )
        } else {
            super.onBackPressed()
        }
    }

    private companion object {
        const val DOUBLE_BACK_CLICK_TIME = 2000L
    }
}