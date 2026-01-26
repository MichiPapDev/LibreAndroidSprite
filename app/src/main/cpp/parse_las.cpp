#include "parse_las.h"
#include <fstream>
#include <android/log.h>

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, "ASE", __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, "ASE", __VA_ARGS__)

bool loadAseHeader(const char* path) {
    std::ifstream file(path, std::ios::binary);
    if (!file) {
        LOGE("No se pudo abrir el archivo");
        return false;
    }

    AseHeader header{};
    file.read((char*)&header, sizeof(AseHeader));

    if (header.magic != 0xA5E0) {
        LOGE("Archivo no es ASE valido");
        return false;
    }

    LOGI("ASE OK");
    LOGI("Frames: %d", header.frames);
    LOGI("Size: %dx%d", header.width, header.height);
    LOGI("Color depth: %d", header.colorDepth);

    return true;
}