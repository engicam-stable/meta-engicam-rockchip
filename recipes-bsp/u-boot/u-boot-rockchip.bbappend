FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-eMMC-enable.patch"

SRC_URI += "file://baudrate-115200.cfg"

# Debug UART baudrate: the DDR init blob sets the UART and passes it via
# ATAGS to the next stages (U-Boot/BL31), so patch it with ddrbin_tool.
RK_DDR_BIN ?= "bin/rk35/rk3568_ddr_1560MHz_v1.19.bin"
RK_DEBUG_BAUDRATE ?= "115200"

do_compile:prepend() {
	sed "s/^uart baudrate=.*/uart baudrate=${RK_DEBUG_BAUDRATE}/" \
		${WORKDIR}/rkbin/tools/ddrbin_param.txt > ${WORKDIR}/ddrbin_param.txt
	${WORKDIR}/rkbin/tools/ddrbin_tool ${RK_SOC_FAMILY} \
		${WORKDIR}/ddrbin_param.txt ${WORKDIR}/rkbin/${RK_DDR_BIN}
}
