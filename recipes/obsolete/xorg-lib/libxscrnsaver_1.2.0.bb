require xorg-lib-common.inc
DESCRIPTION = "X Screen Saver extension library"
LICENSE = "GPL"
DEPENDS += "libxext scrnsaverproto"
PE = "1"
PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "33e54f64b55f22d8bbe822a5b62568cb"
SRC_URI[archive.sha256sum] = "5b067bec0f2eb6405226bde5f20ca107eb0e5c11f5e5e24b455930c450487f85"

RREPLACES_${PN} = "libxss"

XORG_PN = "libXScrnSaver"
