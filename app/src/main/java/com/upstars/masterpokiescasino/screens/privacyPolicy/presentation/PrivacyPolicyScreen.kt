package com.upstars.masterpokiescasino.screens.privacyPolicy.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upstars.masterpokiescasino.R
import com.upstars.masterpokiescasino.ui.components.Header
import com.upstars.masterpokiescasino.ui.theme.forumFontFamily

@Composable
@Suppress("FunctionNaming", "MagicNumber")
fun PrivacyPolicyScreen(
    privacyPolicyScreenViewModel: PrivacyPolicyScreenViewModel,
    onBack: () -> Unit
) {
    val privacyPolicyText = rememberSaveable {
        privacyPolicyScreenViewModel.getPrivacyPolicy()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Header(text = stringResource(R.string.privacy_policy), onBack = onBack)
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = privacyPolicyText,
            fontFamily = forumFontFamily,
            fontSize = 16.sp,
            color = Color(0xFFFFFAF1)
        )
    }
}
