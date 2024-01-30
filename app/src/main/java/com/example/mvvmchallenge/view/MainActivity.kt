package com.example.mvvmchallenge.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder

import com.example.mvvmchallenge.model.data.GetDogResponse
import com.example.mvvmchallenge.ui.theme.MVVMChallengeTheme
import com.example.mvvmchallenge.viewmodel.GetDogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GetDogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = viewModel.dogLiveData.observeAsState()
            MVVMChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(onClick = { viewModel.getDogInfo() }, state = state.value)
                }
            }
        }
    }

}

@Composable
fun MainContent(onClick: () -> Unit, state: GetDogResponse?) {
    Column(modifier = Modifier.fillMaxSize()) {

        AsyncImage(
            model = Builder(LocalContext.current)
                .data(state?.message)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Button(onClick = { onClick() }) {
            Text(text = "GetDog", color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVMChallengeTheme {
        Greeting("Android")
    }
}
