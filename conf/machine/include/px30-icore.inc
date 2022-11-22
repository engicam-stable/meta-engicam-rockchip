# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SOC_FAMILY ?= "px30"
require conf/machine/include/rockchip-arm64-common.inc

KBUILD_DEFCONFIG = "defconfig"

MALI_GPU := "bifrost-g31"
MALI_VERSION := "g2p0"

RK_ISP_VERSION := "1"

DISTRO_FEATURES += " pam wayland opengl"
INIT_MANAGER = "systemd"
BBMASK = "meta-rockchip/recipes-core/systemd/"
