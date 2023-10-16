package com.imaukr.italianfoodukraine.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.imaukr.R


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        //"Що приготуємо?" "Рекомендації"
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontWeight = FontWeight(700),
        color = Color(0xFF494949),
        letterSpacing = 0.4.sp,
    ),

    headlineMedium = TextStyle( // "інгредієнти" процес приготування
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontWeight = FontWeight(400),
        color = Color(0xFF388140),
        letterSpacing = 0.4.sp,

        ),
    titleLarge = TextStyle(
        // title of recipe on one screen
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(700),
        color = Color(0xFF388140),
        letterSpacing = 0.4.sp,
    ),

    headlineSmall = TextStyle(
        // title of recipe in the card
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semi_bold)),
        fontWeight = FontWeight(600),
        color = Color(0xFF388140),
        letterSpacing = 0.4.sp,
    ),
    bodyMedium = TextStyle(
        // grey description of recipe in the card
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(400),
        color = Color(0xFF6B6A6A),
        letterSpacing = 0.4.sp,
    ),

    bodySmall = TextStyle (
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(400),
        color = Color(0xFF444444),
        letterSpacing = 0.4.sp,
    )


)