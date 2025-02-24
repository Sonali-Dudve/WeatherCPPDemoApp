package com.spdfs.weathercppdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spdfs.weathercppdemoapp.constants.KeyConstants
import com.spdfs.weathercppdemoapp.ui.theme.WeatherCPPDemoAppTheme
import com.spdfs.weathercppdemoapp.viewModel.WeatherState
import com.spdfs.weathercppdemoapp.viewModel.WeatherViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherCPPDemoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        val weatherViewModel: WeatherViewModel = viewModel()
                        weatherViewModel.getWeatherData(18.32, 73.85)
                        WeatherScreen(weatherViewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherStateFlow.collectAsState()

    when (weatherState) {

        is WeatherState.Success -> {
            val weatherResponse = (weatherState as WeatherState.Success).weatherResponse
            println("weatherResponse: $weatherResponse")
            ShowWeatherResponse(weatherResponse.toString())
        }

        else -> {
            //val errorMessage = (weatherState as WeatherState.Error).message
            println("Error Occurred")
        }
    }
}


@Composable
fun ShowWeatherResponse(response: String, modifier: Modifier = Modifier) {
    val baseUrl = KeyConstants.getBaseUrl()
    Text(
        text = "Pune Weather Response: \n\n$response",
        modifier = modifier.padding(top = 40.dp).padding(8.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherCPPDemoAppTheme {
        ShowWeatherResponse("Android")
    }
}