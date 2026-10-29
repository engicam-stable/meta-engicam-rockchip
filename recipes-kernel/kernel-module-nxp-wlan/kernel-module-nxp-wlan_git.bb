SUMMARY = "NXP Wi-Fi driver (mlan/moal) for 88W8987 based modules (Panasonic PAN9028)"
DESCRIPTION = "Out-of-tree NXP mwifiex driver (moal.ko + mlan.ko) used by the \
Panasonic PAN9028 SDIO-UART Wi-Fi/Bluetooth combo module."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=ab04ac0f249af12befccb94447c08b77"

# lf-6.1.55_2.2.0 is the NXP release matching the 6.1 kernel
SRCBRANCH = "lf-6.1.55_2.2.0"
SRC_URI = "git://github.com/nxp-imx/mwifiex.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "952d10f3349426f917636a4560974117eb6eef5b"

PV = "6.1.55-2.2.0+git"

S = "${WORKDIR}/git/mxm_wifiex/wlan_src"

inherit module

EXTRA_OEMAKE += "-C ${STAGING_KERNEL_BUILDDIR} M=${S}"

# Load moal at boot with the NXP module parameters file shipped by firmware-nxp-wifi
KERNEL_MODULE_AUTOLOAD += "moal"
KERNEL_MODULE_PROBECONF += "moal"
module_conf_moal = "options moal mod_para=nxp/wifi_mod_para.conf"

RRECOMMENDS:${PN} += "firmware-nxp-wifi-8987"
