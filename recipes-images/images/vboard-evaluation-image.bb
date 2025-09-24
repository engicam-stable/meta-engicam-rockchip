SUMMARY = "A very basic Wayland image with a terminal"

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs weston"

LICENSE = "MIT"

inherit core-image

CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

QB_MEM = "-m 512"

IMAGE_INSTALL:append = " \
        libgpiod-tools \
        libgpiod \
        glmark2 \
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
        zstd \
        gstreamer1.0 \	
        gstreamer1.0-plugins-bad \
        gstreamer1.0-plugins-base \
        gstreamer1.0-plugins-good \
        gstreamer1.0-rockchip  \
		packagegroup-core-full-cmdline \
        alsa-utils \
        alsa-tools \
        alsa-state \
        v4l-rkmpp  \
        v4l-utils  \
"

