package com.wesleyerick.gitcoffe.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wesleyerick.gitcoffe.ui.theme.TitleTopScreen

@Composable
fun GitCoffeTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = TitleTopScreen,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}