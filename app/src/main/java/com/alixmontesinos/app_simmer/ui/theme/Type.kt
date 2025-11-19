package com.alixmontesinos.app_simmer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alixmontesinos.app_simmer.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val NunitoSans = FontFamily(
    Font(R.font.nunito_sans_variable)
)


val ItimFont = FontFamily(
    Font(R.font.itim_regular, FontWeight.Normal)
)

val MontserratSemiregularFond = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal)
)

val MontserratsemiBoldFond = FontFamily(
    Font(R.font.montserrasemibold, FontWeight.Bold)
)

val NunitoSansSemiBold = FontFamily(
    Font(R.font.nunitosemibold)
)