package com.example.skeleton.features.sigin.view

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.example.skeleton.R
import com.example.skeleton.features.sigin.uimodel.SignInIntent
import com.example.skeleton.features.sigin.viewModel.SignInViewModel
import com.example.skeleton.ui.Screen
import com.example.skeleton.ui.component.CustomTextField
import com.example.skeleton.ui.component.FullButton
import com.example.skeleton.ui.theme.ArabicFontFamily
import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun SignInScreen(navController: NavController) {
//    var checked by remember { mutableStateOf(true) }
//    Column {
//
//        Text(text = stringResource(id = R.string.app_name))
//        Button(onClick = {
//            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en")
//// Call this on the main thread as it may require Activity.restart()
//            AppCompatDelegate.setApplicationLocales(appLocale)
//        }) {
//            Text(text = "En")
//        }
//        Button(onClick = {
//            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("ar")
//// Call this on the main thread as it may require Activity.restart()
//            AppCompatDelegate.setApplicationLocales(appLocale)
//        }) {
//            Text(text = "AR")
//        }
//    }
//}
//    Toast.makeText(LocalContext.current,"$checked",Toast.LENGTH_LONG).show()

@Preview
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = koinViewModel(),
    navController: NavController?=null,
) {
    val uiState = viewModel.state.collectAsState().value

    Screen(
        modifier = Modifier.fillMaxSize(),
        error = uiState.error,
        onErrorClick = {
            viewModel.handleIntent(SignInIntent.HideError)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                val imageModifier = Modifier
                    .size(160.dp)
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = imageModifier
                )

                Text(
                    text = stringResource(id = R.string.sign_in), style = TextStyle(
                        fontFamily = ArabicFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                )


                CustomTextField(
                    modifier = Modifier.padding(top = 32.dp),
                    text =uiState.data?.userName?:"" ,
                    onTextChanged = {
                         viewModel.handleIntent(SignInIntent.UpdateUsername(it))
                    },
                    label = {
                        Text(text = stringResource(id = R.string.username))
                    },
                    error = uiState.data?.userNameError ?: "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )

                CustomTextField(
                    text = uiState.data?.password ?: "",
                    onTextChanged = {
                        viewModel.handleIntent(SignInIntent.UpdatePassword(it))
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.password),
                        )
                    },
                    error = uiState.data?.passwordError ?: "",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                FullButton(
                    modifier = Modifier.padding(top = 24.dp),
                    textString = stringResource(id = R.string.sign_in),
                    backgroundColor = Color.Cyan,
                    textColor = Color.Black,
                    isLoading = uiState.isLoading,
                    onClick = {
                        if (!uiState.isLoading) viewModel.handleIntent(SignInIntent.SignIn)
                    }
                )


            }


            Image(
                modifier = Modifier
                    .height(180.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.sign_in),
                contentScale = ContentScale.FillWidth,
            )
        }


        LaunchedEffect(key1 = uiState.data?.navigateToHome) {
            if (uiState.data != null && uiState.data.navigateToHome)
                navController?.popBackStack()
        }
    }

}



