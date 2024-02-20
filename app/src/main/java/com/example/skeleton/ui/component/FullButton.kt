package com.example.skeleton.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeleton.ui.theme.ArabicFontFamily

@Composable
fun FullButton(
    modifier: Modifier = Modifier,
    textString: String,
    backgroundColor: Color,
    textColor: Color,
    isLoading: Boolean = false,
    onClick: () -> Unit,
    borderStroke: BorderStroke? = null
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(26.dp),
        border = borderStroke,
        onClick = onClick
    ) {
        if (isLoading)
            CircularProgressIndicator(
                color = textColor,
                modifier = Modifier
                    .size(32.dp)
            )
        else
            Text(
                text = (textString),
                style = TextStyle(
                    color = textColor,
                    fontFamily = ArabicFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
    }
}