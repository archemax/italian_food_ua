package com.imaukr.italianfoodukraine.user_interface

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import com.imaukr.italianfoodukraine.user_interface.my_navigation_routes.NavigationComponent
import com.imaukr.italianfoodukraine.ui.theme.ItalianFoodTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItalianFoodTheme {
                NavigationComponent()
            }
        }
    }
}

