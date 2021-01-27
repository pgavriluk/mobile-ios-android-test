package data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Emulators {

    ANDROID_EMULATOR("Android Emulator", List.of("5.1", "6.0", "8.0")),
    ANDROID_GOOGLEAPI_EMULATOR("Android GoogleAPI Emulator", List.of("5.1", "6.0", "7.0", "7.1", "8.0", "8.1", "9.0", "10.0")),
    SAMSUNG_GALAXY_TAB_S3_GOOGLEAPI_EMULATOR("Samsung Galaxy Tab S3 GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_TAB_A_10_GOOGLEAPI_EMULATOR("Samsung Galaxy Tab A 10 GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S9_WQHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S9 WQHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1", "9.0")),
    SAMSUNG_GALAXY_S9_PLUS_WQHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S9 Plus WQHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S9_PLUS_HD_GOOGLEAPI_EMULATOR("Samsung Galaxy S9 Plus HD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S9_PLUS_FHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S9 Plus FHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S9_HD_GOOGLEAPI_EMULATOR("Samsung Galaxy S9 HD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1", "9.0")),
    SAMSUNG_GALAXY_S9_FHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S9 FHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1", "9.0")),
    SAMSUNG_GALAXY_S8_WQHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 WQHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_PLUS_WQHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 Plus WQHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_PLUS_HD_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 Plus HD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_PLUS_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 Plus GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_PLUS_FHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 Plus FHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_HD_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 HD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S8_FHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S8 FHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_WQHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 WQHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_HD_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 HD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_FHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 FHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_EDGE_WQHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 Edge WQHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_EDGE_HD_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 Edge HD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_EDGE_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 Edge GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S7_EDGE_FHD_GOOGLEAPI_EMULATOR("Samsung Galaxy S7 Edge FHD GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    SAMSUNG_GALAXY_S6_GOOGLEAPI_EMULATOR("Samsung Galaxy S6 GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    GOOGLE_PIXEL_GOOGLEAPI_EMULATOR("Google Pixel GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    GOOGLE_PIXEL_C_GOOGLEAPI_EMULATOR("Google Pixel C GoogleAPI Emulator", List.of("7.0", "7.1", "8.0", "8.1")),
    GOOGLE_PIXEL_3A_XL_GOOGLEAPI_EMULATOR("Google Pixel 3a XL GoogleAPI Emulator", List.of("10.0")),
    GOOGLE_PIXEL_3A_GOOGLEAPI_EMULATOR("Google Pixel 3a GoogleAPI Emulator", List.of("10.0")),
    GOOGLE_PIXEL_3_XL_GOOGLEAPI_EMULATOR("Google Pixel 3 XL GoogleAPI Emulator", List.of("9.0", "10.0")),
    GOOGLE_PIXEL_3_GOOGLEAPI_EMULATOR("Google Pixel 3 GoogleAPI Emulator", List.of("9.0", "10.0"));


    private String name;
    private List<String> operatingSystems;


}
