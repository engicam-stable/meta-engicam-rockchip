# meta-engicam-rockchip

Yocto BSP layer for the Rockchip SOC boards
  - wiki <http://opensource.rock-chips.com/wiki_Main_Page>.

This README file contains information on building and booting the meta-rockchip BSP layers.

Please see the corresponding sections below for details.

## Dependencies

This layer depends on:

* URI: git://git.yoctoproject.org/poky
* branch: kirkstone

* URI: git://git.openembedded.org/meta-openembedded
* layers: meta-oe
* branch: kirkstone

* URI: git://github.com/JeffyCN/meta-rockchip.git
* branch: kirkstone

## Table of Contents

I. Configure yocto/oe Environment

II. Building meta-engicam-rockchip BSP Layers

### I. Configure yocto/oe Environment

In order to build an image with BSP support for a given release, you need to download the corresponding layers described in the "Dependencies" section. Be sure that everything is in the same directory.

```shell
~ $ mkdir yocto; cd yocto
~/yocto $ git clone git://git.yoctoproject.org/poky -b kirkstone
~/yocto $ cd poky
~/yocto/poky $ git clone git://git.openembedded.org/meta-openembedded.git -b kirkstone
~/yocto/poky $ git clone https://github.com/JeffyCN/meta-rockchip.git -b kirkstone
~/yocto/poky $ cd meta-rockchip
~/yocto/poky/meta-rockchip $ git checkout 14f41dafb59166992b078aeb65eae2163c6db0f9
~/yocto/poky/meta-rockchip $ cd ..
~/yocto/poky $ git clone https://github.com/engicam-stable/meta-engicam-rockchip.git -b kirkstone
```

Then you need to source the configuration script:

```shell
~/yocto $ source poky/oe-init-build-env
```

Having done that, you can build a image for a rockchip board by adding the location of the meta-rockchip layer to bblayers.conf, along with any other layers needed.

```
~/yocto $ bitbake-layers add-layer ../poky/meta-openembedded/meta-oe
~/yocto $ bitbake-layers add-layer ../poky/meta-rockchip
~/yocto $ bitbake-layers add-layer ../poky/meta-engicam-rockchip
```

Now you need to edit the **conf/local.conf** file inserting your machine, to check the available machines look in the **conf/machine** of this meta-layer.

```
  MACHINE = "px30-icore-starterkit"
```


### II. Building meta-rockchip BSP Layers

You should then be able to build a image as such:

```shell
$ bitbake engicam-image-weston
```

At the end of a successful build, you should have an .wic image in `/path/to/yocto/build/tmp/deploy/images/<MACHINE>/`.