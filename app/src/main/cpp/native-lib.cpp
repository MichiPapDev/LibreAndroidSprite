#include <jni.h>
#include <string>
#include "parse_las.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_libre_androidsprite_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "hola desde el c++";
    loadAseHeader("/data/data/com.libre.androidsprite/files/input.ase");
    return env->NewStringUTF(hello.c_str());
}