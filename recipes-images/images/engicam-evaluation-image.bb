SUMMARY = "A very basic Wayland image with a terminal"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs weston"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "gtk+3-demo"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

QB_MEM = "-m 512"

IMAGE_INSTALL:append = " \
	libgpiod-tools \
	libgpiod \
	glmark2 \
	canutils \
	cantest \
	serialtools \
	devmem2 \
	dosfstools \
	e2fsprogs \
	ethtool \
	evtest \
	i2c-tools \
	iproute2 \
	minicom \
	parted \
	e2fsprogs-resize2fs \
	usbutils \
	ldd \
	zstd \
	stress-ng \
	iperf3 \
	gstreamer1.0 \	
	gstreamer1.0-plugins-bad \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugins-good \
	gstreamer1.0-rockchip  \
	brcm43430-firmware \
	rk-brcm \
	brcm-patchram-plus \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	iw wpa-supplicant \
	alsa-utils \
	alsa-tools \
	alsa-state \
	v4l-rkmpp  \
	v4l-utils  \
"

fix_bcm43430() { 
  cd ${IMAGE_ROOTFS}/lib/firmware/brcm
  ln -sf brcmfmac43430-sdio.bin brcmfmac43430-sdio.rockchip,rk3568-icore.bin
}

ROOTFS_POSTPROCESS_COMMAND:append = " fix_bcm43430;"


# Partition layout: same as meta-rockchip generic-gptdisk, but with a
# fixed-size boot partition (boot.img) to leave room for bigger kernels/dtbs.
WKS_FILE = "engicam-gptdisk.wks.in"
RK_BOOT_PART_SIZE ?= "64M"
