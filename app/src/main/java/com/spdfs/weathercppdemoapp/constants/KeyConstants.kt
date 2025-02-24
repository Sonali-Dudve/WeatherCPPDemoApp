package com.spdfs.weathercppdemoapp.constants

object KeyConstants {

    init {
        System.loadLibrary("native-main-lib")
    }

    external fun getWeatherAPIKey(): String
    external fun getBaseUrl(): String
}