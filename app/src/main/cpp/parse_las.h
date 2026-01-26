#pragma once
#include <cstdint>

#pragma pack(push, 1)
struct AseHeader {
    uint32_t fileSize;
    uint16_t magic;      // 0xA5E0
    uint16_t frames;
    uint16_t width;
    uint16_t height;
    uint16_t colorDepth; // 32 = RGBA
    uint32_t flags;
    uint16_t speed;
    uint32_t reserved1;
    uint32_t reserved2;
    uint8_t  paletteEntry;
    uint8_t  reserved3[3];
    uint16_t numColors;
    uint8_t  pixelWidth;
    uint8_t  pixelHeight;
    int16_t  gridX;
    int16_t  gridY;
    uint16_t gridWidth;
    uint16_t gridHeight;
    uint8_t  reserved4[84];
};
#pragma pack(pop)

bool loadAseHeader(const char* path);