package com.agt.kds.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.agt.kds.presentation.ui.theme.KDSTheme


@Composable
fun inKdsTheme(content : @Composable ()->Unit) {
    KDSTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}