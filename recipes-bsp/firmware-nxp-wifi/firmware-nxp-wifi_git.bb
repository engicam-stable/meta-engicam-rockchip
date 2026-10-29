SUMMARY = "NXP Wi-Fi/Bluetooth firmware for 88W8987 based modules (Panasonic PAN9028)"
DESCRIPTION = "Firmware and module parameters for the NXP 88W8987 chipset \
(SDIO-UART combo) used by the Panasonic PAN9028 module."
# NXP proprietary license, see LICENSE.txt in the imx-firmware repository
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=2827219e81f28aba7c6a569f7c437fa7"

# Keep in sync with kernel-module-nxp-wlan SRCBRANCH
SRCBRANCH = "lf-6.1.55_2.2.0"
SRC_URI = "git://github.com/nxp-imx/imx-firmware.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "7be5a936ce8677962dd7b41c6c9f41dd14350bec"

PV = "6.1.55-2.2.0+git"

S = "${WORKDIR}/git"

inherit allarch

do_compile[noexec] = "1"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware/nxp
	install -m 0644 ${S}/nxp/wifi_mod_para.conf ${D}${nonarch_base_libdir}/firmware/nxp/
	install -m 0644 ${S}/nxp/FwImage_8987/sdiouart8987_combo_v0.bin ${D}${nonarch_base_libdir}/firmware/nxp/
	install -m 0644 ${S}/nxp/FwImage_8987/sd8987_wlan.bin ${D}${nonarch_base_libdir}/firmware/nxp/
	install -m 0644 ${S}/nxp/FwImage_8987/uartuart8987_bt.bin ${D}${nonarch_base_libdir}/firmware/nxp/
	install -m 0644 ${S}/nxp/FwImage_8987/ed_mac_ctrl_V3_8987.conf ${D}${nonarch_base_libdir}/firmware/nxp/
	install -m 0644 ${S}/nxp/FwImage_8987/txpwrlimit_cfg_8987.conf ${D}${nonarch_base_libdir}/firmware/nxp/
}

PACKAGES = "${PN}-common ${PN}-8987"

FILES:${PN}-common = "${nonarch_base_libdir}/firmware/nxp/wifi_mod_para.conf"
FILES:${PN}-8987 = "${nonarch_base_libdir}/firmware/nxp/*8987*"

RDEPENDS:${PN}-8987 += "${PN}-common"
