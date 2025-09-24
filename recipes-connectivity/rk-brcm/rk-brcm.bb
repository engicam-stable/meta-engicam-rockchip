
LICENSE = "CLOSED"


SRC_URI += "file://brcmfmac43430-sdio.rockchip,rk3568-icore.txt"
SRC_URI += "file://brcmfmac43430-sdio.bin"
SRC_URI += "file://brcmfmac43430-sdio.clm_blob"

do_install() {
	install -d ${D}/lib/firmware/brcm/
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.rockchip,rk3568-icore.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.rockchip,rk3568-icore.txt
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.bin ${D}/lib/firmware/brcm/brcmfmac43430-sdio.bin
	install -m 0755 ${WORKDIR}/brcmfmac43430-sdio.clm_blob ${D}/lib/firmware/brcm/brcmfmac43430-sdio.clm_blob
}

FILES:${PN} = "/lib/firmware/brcm/*"

