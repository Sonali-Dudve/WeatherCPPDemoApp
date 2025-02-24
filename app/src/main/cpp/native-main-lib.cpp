//
// Created by 1746SD on 24-02-2025.
//

#include <jni.h>
#include <string>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/system_properties.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_spdfs_weathercppdemoapp_constants_KeyConstants_getWeatherAPIKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "0eeb4ce49778178c6780f5748911754d";
    return env->NewStringUTF(api_key.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_spdfs_weathercppdemoapp_constants_KeyConstants_getBaseUrl(JNIEnv *env, jobject thiz) {
    std::string base_url = "https://api.openweathermap.org/data/2.5/";
    return env->NewStringUTF(base_url.c_str());
}