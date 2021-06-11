#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_moviecatalogue_core_utils_Constants_getApiKeys(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "6d4359e131e2493dedd72daec5c5229a");
}

JNIEXPORT jstring JNICALL
Java_com_moviecatalogue_core_utils_Constants_getBaseUrl(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "https://api.themoviedb.org/3/");
}

JNIEXPORT jstring JNICALL
Java_com_moviecatalogue_core_utils_Constants_getHostName(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "api.themoviedb.org");
}