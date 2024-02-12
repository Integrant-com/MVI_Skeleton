package com.example.skeleton.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skeleton.ui.theme.ArabicFontFamily

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    //placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    //trailingIcon: @Composable (() -> Unit)? = null,
    error: String? = null,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    return OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = onTextChanged,
        maxLines = 1,
        leadingIcon = leadingIcon,
        label = label,
        isError = !error.isNullOrEmpty(),
        visualTransformation = visualTransformation,
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = error ?: "",
                textAlign = TextAlign.Start,
            )
        },

        //        colors = TextFieldDefaults.outlinedTextFieldColors(
        //            focusedBorderColor = colorResource(id = R.color.color_app_second),
        //            unfocusedBorderColor = colorResource(id = R.color.color_very_light_grey),
        //            leadingIconColor = colorResource(id = R.color.color_app_second),
        //            focusedLabelColor = colorResource(id = R.color.color_app_second),
        //            unfocusedLabelColor = colorResource(id = R.color.color_very_light_grey),
        //            cursorColor = colorResource(id = R.color.color_app_second),
        //        ),
        shape = RoundedCornerShape(50.dp),
        textStyle = TextStyle(
            fontFamily = ArabicFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
        keyboardOptions = keyboardOptions,
    )
}

