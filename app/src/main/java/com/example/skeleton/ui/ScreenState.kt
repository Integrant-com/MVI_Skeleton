package com.example.skeleton.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeleton.R
import com.example.skeleton.domain.uistate.Error
import com.example.skeleton.ui.theme.ArabicFontFamily

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    showLoading: Boolean = false,
    error: Error? = null,
    onErrorClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Box(modifier) {
        content()
        ScreenState(modifier, showLoading, error, onErrorClick)
    }
}


@Composable
fun ScreenState(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error: Error? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.Cyan,
                modifier = Modifier
                    .size(64.dp)
            )
        }
        when (error) {
            is Error.ServerError -> ShowError(errorMessage = error.message, dismissButton = {
                Button(
                    onClick = { onClick?.invoke() }) {
                    Text(stringResource(id = R.string.dismiss))
                }
            })
            else -> {}
        }
    }
}

@Composable
fun ShowError(
    modifier: Modifier = Modifier,
    errorMessage: String,
    dismissButton: @Composable (() -> Unit)? = null,
) {

    AlertDialog(modifier = modifier.fillMaxWidth(),
        onDismissRequest = {
//            openDialog.value = false
        },
        title = {
            Text(
                text = stringResource(id = R.string.error),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = ArabicFontFamily, fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            )
        },
        text = {
            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = ArabicFontFamily, fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            )
        }, confirmButton = {},
        dismissButton = dismissButton

    )

}
