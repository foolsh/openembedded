require gtk+.inc

PR = "${INC_PR}.1"

SRC_URI += "file://cellrenderer-cairo.patch;striplevel=0 \
           file://entry-cairo.patch;striplevel=0 \
           file://scrolled-placement.patch;striplevel=0"
# temporary
#           file://gtklabel-resize-patch;apply=yes \
#           file://menu-deactivate.patch \
#           file://combo-arrow-size.patch;striplevel=0 \
# die die die
#           file://pangoxft2.10.6.diff"

EXTRA_OECONF = "--with-libtiff --disable-xkb --disable-glibtest --enable-display-migration"

PACKAGES_DYNAMIC = "gdk-pixbuf-loader-* gtk-immodule-* gtk-printbackend-*"

python populate_packages_prepend () {
	import os.path

	prologue = bb.data.getVar("postinst_prologue", d, 1)

	gtk_libdir = bb.data.expand('${libdir}/gtk-2.0/${LIBV}', d)
	loaders_root = os.path.join(gtk_libdir, 'loaders')
	immodules_root = os.path.join(gtk_libdir, 'immodules')
	printmodules_root = os.path.join(gtk_libdir, 'printbackends');

	do_split_packages(d, loaders_root, '^libpixbufloader-(.*)\.so$', 'gdk-pixbuf-loader-%s', 'GDK pixbuf loader for %s', prologue + 'gdk-pixbuf-query-loaders > /etc/gtk-2.0/gdk-pixbuf.loaders')
	do_split_packages(d, immodules_root, '^im-(.*)\.so$', 'gtk-immodule-%s', 'GTK input module for %s', prologue + 'gtk-query-immodules-2.0 > /etc/gtk-2.0/gtk.immodules')
	do_split_packages(d, printmodules_root, '^libprintbackend-(.*)\.so$', 'gtk-printbackend-%s', 'GTK printbackend module for %s')

        if (bb.data.getVar('DEBIAN_NAMES', d, 1)):
                bb.data.setVar('PKG_${PN}', 'libgtk-2.0', d)
}

SRC_URI[gtk.md5sum] = "f7aab88e856a813386f797aade5867ad"
SRC_URI[gtk.sha256sum] = "c447b7f42c75ed5bea3e5488a0af82c0f5ed04536ddbf04c9f2f4f42815ba4a9"