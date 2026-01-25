#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_libre_androidsprite_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "hola desde el c++";
    return env->NewStringUTF(hello.c_str());
}