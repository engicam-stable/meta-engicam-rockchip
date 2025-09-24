SUMMARY = "Firmware for Broadcom BCM43430A1 WiFi/Bluetooth combo chip"
DESCRIPTION = "Firmware file required for Bluetooth functionality with BCM43430A1 (1DX module)"
LICENSE = "CLOSED"

SRC_URI = "file://BCM43430A1.1DX.hcd"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/etc/firmware
    install -m 0644 ${S}/BCM43430A1.1DX.hcd ${D}/etc/firmware
}

FILES:${PN} += "/etc/firmware/BCM43430A1.1DX.hcd"

