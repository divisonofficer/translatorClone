package edu.skku.cs.translatorclone.activity.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import edu.skku.cs.translatorclone.activity.ui.theme.Background10
import edu.skku.cs.translatorclone.activity.ui.theme.TranslatorcloneTheme
import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel


@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<TranslatorViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Background10.value.toInt()

        setContent {
            TranslatorcloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainLayout(viewModel)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@ExperimentalComposeUiApi
@Composable
fun MainLayout(viewModel: TranslatorViewModel){
    Column(
        Modifier
            .background(Background10)
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center) {

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.weight(1f)) {

            val text by viewModel.resultText.collectAsState()
            Text(text = text,color = Color.White, fontSize = 40.sp, modifier = Modifier.padding(16.dp))
        }
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(
                Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth(1f)){
                val text by viewModel.resultText.collectAsState()
                TextField(value = text, onValueChange = {
                                                      viewModel.setResultText(it)
                }, modifier = Modifier.onFocusChanged {


                }.onKeyEvent {

                    when(it.key){
                        Key.Back -> {

                        }
                    }

                   false
                }.clearFocusOnKeyboardDismiss(),
                    textStyle = TextStyle(
                        fontSize = 32.sp
                    )
                    , colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent

                ), placeholder = {

                    Text(text = "입력해 주세요", color = Color.Gray, fontSize = 32.sp)
                })
            }
        }
    }
}



fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember { mutableStateOf(false) }
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (isFocused) {
       val imeIsVisible = LocalView.current.rootWindowInsets.isVisible(WindowInsets.Type.ime())
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TranslatorcloneTheme {
        Column(
            Modifier
                .height(720.dp)
                .width(360.dp), ) {


            MainLayout(TranslatorViewModel())

        }
    }
}