
# meta-engicam-rockchip Layer

This README file provides information about the contents of the **meta-engicam-rockchip** layer.


## Boards supported

|SOM                   |                BOARD                |
|----------------------|-------------------------------------|
|rk3568-icore          |starterkit 2.0                       |
|rk3568-vboard         |VBoard SBC                           |

For more information, refer to:  

- [Starter Kit 2.0 Documentation](docs/starterkit2.0.md)
- [VBoard Documentation](docs/vboard.md)



# How to Build the Evaluation Image

## Prepare the Environment

if you are planning to use a virtual environment on your linux machine please refear to [create distrobox image](docs/distrobox.md).


If the environment is already set up, you can skip to the [Rebuild the Image](#rebuild-the-image) section.

Clone the required repositories:

```bash
git clone git://git.yoctoproject.org/poky -b scarthgap
git clone git://git.openembedded.org/meta-openembedded -b scarthgap
git clone https://github.com/JeffyCN/meta-rockchip -b scarthgap
# Place the meta-engicam-rockchip layer in the same directory.
```

Initialize the build environment:

```bash
source poky/oe-init-build-env
```

Edit your `bblayers.conf` file to include the following layers:

```conf
# build/conf/bblayers.conf
BBLAYERS ?= " \
  ${TOPDIR}/../meta-rockchip \
  ${TOPDIR}/../poky/meta \
  ${TOPDIR}/../poky/meta-poky \
  ${TOPDIR}/../poky/meta-yocto-bsp \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-engicam-rockchip \
"
```

Set the machine in `local.conf`:

```conf
MACHINE = "rk3568-icore"
```

or

```conf
MACHINE = "rk3568-vboard"
```


Append the following line in the same file:

```conf
INHERIT:append = " rockchip-image"
```

Then build the image:

```bash
bitbake engicam-evaluation-image
```

or

```bash
bitbake vboard-evaluation-image
```

## Rebuild the Image

If the environment is already configured, you can rebuild the image using:

```bash
source poky/oe-init-build-env
```

```bash
bitbake engicam-evaluation-image
```

or

```bash
bitbake vboard-evaluation-image
```