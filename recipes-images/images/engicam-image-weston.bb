SUMMARY = "A very basic Wayland image with a terminal"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs weston"
LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "gtk+3-demo engicam-rockchip-startup"
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

QB_MEM = "-m 512"

IMAGE_INSTALL += " \
util-linux \
i2c-tools \
serialtools \
minicom \
packagegroup-core-tools-testapps \
packagegroup-base \
packagegroup-core-weston \
px30-brcm \
alsa-utils-scripts \
"
