
LICENSE = "CLOSED"

SRC_URI += "file://laird-lwb-etsi-firmware-10.4.0.10.tar.bz2"


do_install:append () {
	cd ${WORKDIR}/lib
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/lib/firmware/brcm/* ${D}/lib/firmware/brcm/
}

FILES:${PN} = "/*"

