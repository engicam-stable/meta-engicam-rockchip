
LICENSE = "CLOSED"

SRC_URI += "file://laird-lwb-etsi-firmware-10.4.0.10.tar.bz2"


do_install () {
	cd ${WORKDIR}/lib
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/lib/firmware/brcm/* ${D}/lib/firmware/brcm/
	ln -sf brcmfmac43430-sdio-prod_v7.45.98.118.bin brcmfmac43430-sdio.engicam,px30-core.bin 
	ln -sf brcmfmac43430-sdio-etsi.txt brcmfmac43430-sdio.engicam,px30-core.txt 
}

FILES:${PN} = "/*"

