SUMMARY = "A very basic Wayland image with a terminal"

IMAGE_FEATURES += " \
splash \
package-management \
ssh-server-dropbear \
hwcodecs \
weston \
"
LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "gtk+3-demo engicam-rockchip-startup"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

QB_MEM = "-m 512"

IMAGE_INSTALL += " \
packagegroup-core-tools-testapps \
packagegroup-base \
packagegroup-core-weston \
util-linux \
i2c-tools \
serialtools \
minicom \
px30-brcm \
alsa-utils-scripts \
evtest \
bluez5 \
iw \
gstreamer1.0 \
gstreamer1.0-plugins-bad \
gstreamer1.0-plugins-base \
gstreamer1.0-plugins-good \
gstreamer1.0-rockchip \
gst-examples \
strace \
"

fix_bcm43430() { 
    cd ${IMAGE_ROOTFS}/lib/firmware/brcm
	ln -sf brcmfmac43430-sdio-prod_v7.45.98.124.bin brcmfmac43430-sdio.engicam,px30-core.bin 
	ln -sf brcmfmac43430-sdio-etsi.txt brcmfmac43430-sdio.engicam,px30-core.txt 
}
ROOTFS_POSTPROCESS_COMMAND:append:px30 = "fix_bcm43430;"