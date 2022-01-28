package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalkulator.ui.theme.KalkulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            KalkulatorTheme{
                myApp()

                // A surface container using the 'background' color from the theme


            }
        }
    }
}

@Composable
fun myApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else{
        Greetings()
    }

}

@Composable
private fun Greetings(names : List<String> = listOf("World", "Compose")){
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names){
            Greeting(name = name )
        }
    }

}


@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    KalkulatorTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}



@Composable
private fun Greeting(name: String) {
    var expanded = remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState (
        if (expanded.value) 48.dp else 0.dp,
                animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)

            }


            OutlinedButton(
                onClick = {expanded.value = !expanded.value}
            ) {
                Text(if (expanded.value)"Show less" else "Show more")
            }
        }
        //Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun DefaultPreview() {
    KalkulatorTheme {
        Greetings()


    }
}
