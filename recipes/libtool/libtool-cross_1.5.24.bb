require libtool_${PV}.bb

PR = "${INC_PR}.0"
PACKAGES = ""
SRC_URI_append = " file://libdir-la.patch \
                   file://prefix.patch \
                   file://tag.patch \
                   file://tag1.patch \
                   file://install-path-check.patch"
S = "${WORKDIR}/libtool-${PV}"

prefix = "${STAGING_DIR_NATIVE}${prefix_native}"
exec_prefix = "${STAGING_DIR_NATIVE}${prefix_native}"
bindir = "${STAGING_BINDIR_NATIVE}"

do_compile () {
	:
}

do_stage () {
        install -m 0755 ${HOST_SYS}-libtool ${bindir}/${HOST_SYS}-libtool
        install -m 0644 libltdl/ltdl.h ${STAGING_INCDIR}/
        install -d ${STAGING_DIR_HOST}${target_datadir}/libtool ${STAGING_DIR_HOST}${target_datadir}/aclocal
        install -c config.guess ${STAGING_DIR_HOST}${target_datadir}/libtool/
        install -c config.sub ${STAGING_DIR_HOST}${target_datadir}/libtool/
        install -c -m 0644 ltmain.sh ${STAGING_DIR_HOST}${target_datadir}/libtool/
        install -c -m 0644 libtool.m4 ${STAGING_DIR_HOST}${target_datadir}/aclocal/
        install -c -m 0644 ltdl.m4 ${STAGING_DIR_HOST}${target_datadir}/aclocal/
}

do_install () {
	:
}

SRC_URI[md5sum] = "d0071c890101fcf4f2be8934a37841b0"
SRC_URI[sha256sum] = "1e54016a76e9704f11eccf9bb73e2faa0699f002b00b6630df82b8882ff2e5b2"