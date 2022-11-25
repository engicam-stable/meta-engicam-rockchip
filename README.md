# meta-engicam-rockchip

This README file contains information on building and booting the meta-engicam-rockchip BSP layers.

Please see the corresponding sections below for details.

## Dependencies

This layer depends on:

* URI: git://git.yoctoproject.org/poky
* branch: honister

* URI: https://github.com/openembedded/meta-openembedded.git
* layers: meta-oe
* branch: honister

* URI: https://github.com/JeffyCN/meta-rockchip.git
* layers: meta-rockchip
* branch: honister

## Table of Contents

I. Configure yocto/oe Environment

II. Building meta-engicam-rockchip BSP Layers

III. Booting your Device

IV. Tested Hardwares

V. Supporting new Machine

### I. Configure yocto/oe Environment

In order to build an image with BSP support for a given release, you need to download the corresponding layers described in the "Dependencies" section. Be sure that everything is in the same directory.

```shell
~ $ mkdir yocto; cd yocto
~/yocto $ git clone git://git.yoctoproject.org/poky -b honister
~/yocto $ cd poky
~/yocto/poky $ git clone https://github.com/openembedded/meta-openembedded.git -b honister
~/yocto/poky $ git clone https://github.com/JeffyCN/meta-rockchip.git -b honister
~/yocto/poky $ cd meta-rockchip
~/yocto/poky/meta-rockchip $ git checkout 7b13a4aecadcde002a1af9a246813c61ab6cc559
~/yocto/poky/meta-rockchip $ cd ..
~/yocto/poky $ git clone https://github.com/engicam-stable/meta-engicam-rockchip.git -b honister
```

And put the meta-engicam-rockchip layer here too.

Then you need to source the configuration script:

```shell
~/yocto $ source poky/oe-init-build-env
```

Having done that, you can build a image for a engicam board by adding the location of the meta-engicam-rockchip layer to bblayers.conf, along with any other layers needed.

For example:

```makefile
# build/conf/bblayers.conf
BBLAYERS ?= " \
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
  ${TOPDIR}/../poky/meta-yocto-bsp \
  ${TOPDIR}/../poky/meta-openembedded/meta-oe \
  ${TOPDIR}/../poky/meta-rockchip \
  ${TOPDIR}/../poky/meta-engicam-rockchip \
```

To enable a particular machine, you need to add a MACHINE line naming the BSP to the local.conf file:

```makefile
  MACHINE = "px30-icore-starterkit"
  DISTRO_FEATURES:remove += " x11"
```

All supported machines can be found in **meta-engicam-rockchip/conf/machine**.

### II. Building meta-engicam-rockchip BSP Layers

You should then be able to build a image as such:

```shell
$ bitbake engicam-image-weston
```

At the end of a successful build, you should have an .wic image in /path/to/yocto/build/tmp/deploy/\<MACHINE\>/.

### III. Booting your Device

```shell
$ sudo dd if=engicam-image-weston-px30-icore.wic of=/dev/sdb bs=64M && sync
```

### IV. Tested Hardwares

The following undergo regular basic testing with their respective MACHINE types.

* px30 icore starterkit 2.0 board
* px30 icore c.touch 2.0 board + AMP 10.1 inch Display
