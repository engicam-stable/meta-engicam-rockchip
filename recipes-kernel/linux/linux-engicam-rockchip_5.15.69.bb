# Copyright (C) 2021, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-kernel/linux/linux-yocto.inc
require linux-rockchip.inc

inherit freeze-rev local-git

SRCREV = "8f689d7e9a6d4efe04558f1b1e47031cd766d549"
SRC_URI = " \
	git://github.com/engicam-stable/linux-engicam-rockchip.git;protocol=https;branch=linux-5.15.y; \
	file://${THISDIR}/files/cgroups.cfg \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "5.15.69"

SRC_URI:append = " ${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', \
		   'file://${THISDIR}/files/ext4.cfg', \
		   '', \
		   d)}"
