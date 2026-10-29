
# Starter Kit 2.0 Bring-Up

## U-Boot

| Status | Test      | Notes                                           |
|--------|-----------|-------------------------------------------------|
| TBT    | Ethernet  | Tested using `ping`                             |
| OK     | SD Card   | Booted system with **JM1** jumper closed        |
| OK     | eMMC      | Booted system with **JM1** jumper open          |
| TBT    | USB       | Tested with `usb start`, `usb tree`, `usb stop` |

---

## Kernel

| Status | Test                        | Notes                                              |
|--------|-----------------------------|----------------------------------------------------|
| OK     | GMAC 0                      | Network communication                              |
| OK     | GMAC 1                      | Network communication                              |
| OK     | USB2                        | Tested with USB stick on J8/J10                    |
| OK     | USB3                        | Tested with USB stick on J11                       |
| OK     | SD Card                     | Booted system from SD card. JM1 closed             |
| OK     | eMMC                        | Booted system from eMMC. JM1 open                  |
| OK     | UART 232                    | J21 UART7                                          |
| TBT    | UART 485                    | J22 UART0                                          |
| OK     | Linux Console               | Console I/O working                                |
| OK     | Touchscreen                 | Manually tested                                    |
| OK     | LVDS 0                      | Display output working                             |
| OK     | HDMI                        | Display output working                             |
| OK     | Backlight Control           |                                                    |
| OK     | AUDIO                       | Tested with `aplay` and `arecord`                  |
| TBT    | PCI-e                       |                                                    |
| OK     | Wi-Fi                       |                                                    |
| OK     | Bluetooth                   |                                                    |
| TBT    | MIPI-CSI                    |                                                    |
| OK     | CAN_BUS_A / CAN_BUS_B       |                                                    |
| OK     | Reboot                      |                                                    |
| OK     | Reboot Button               |                                                    |
| OK     | RTC                         |                                                    |
| OK     | NPU                         | Detected in `dmesg`. No functional test performed. |
| OK     | GPU                         | Tested with `glmark2` under Weston                 |
| OK     | VPU                         | Tested with GStreamer under Weston                 |
| OK     | PCAL6416                    |                                                    |

---

## debug console 

The console port is on J26. With the criteria:

115200 8,n,1

es.

```bash
tio /dev/ttyUSB0 -b 115200
```


## Camera Input

Image shows incorrect colorspace.

```bash
export XDG_RUNTIME_DIR=/run/user/1000/
gst-launch-1.0 v4l2src device=/dev/video0 ! waylandsink sync=false
```

---

## NPU

```bash
[    3.995694] rockchip,bus bus-npu: bin=0
[    3.995730] rockchip,bus bus-npu: Failed to get leakage
...
[    4.362923] RKNPU fde40000.npu: l=0 h=2147483647 hyst=5000 l_limit=0 h_limit=0 h_table=0
```

---

## Wi-Fi

The `brcmfmac.ko` driver must be loaded manually:

```bash
ifconfig wlan0 up
iw dev wlan0 scan | grep SSID
```

---

## Bluetooth

```bash
brcm_patchram_plus --patchram /etc/firmware/BCM43430A1.1DX.hcd --enable_hci \
--no2bytes --tosleep 50000 --baudrate 1000000 \
--use_baudrate_for_download /dev/ttyUSB0 &

hciconfig hci0 up
bluetoothctl 
# Then:
# scan on
```

---

## RTC

```bash
dmesg | grep rtc
[    3.631496] rk808-rtc rk808-rtc: registered as rtc1
[    3.635039] rtc-pcf8523 4-0068: registered as rtc0
[    3.635390] rtc-pcf8523 4-0068: hctosys: unable to read the hardware clock
```

---

## Backlight

Controlled via **PWM4**.

---

## CAN Interfaces

- **CAN1 → J23** → SOM pin 119 RX / 118 TX → `CAN0_TX_M0`  
- **CAN2 → J24** → SOM pin 121 RX / 120 TX → `CAN2_RX_M0`

```bash
ip link set can0 type can bitrate 125000
ip link set can1 type can bitrate 125000

ifconfig can0 up
ifconfig can1 up

cantest can0 &
cantest can1 5A1#11.2233.44556677.88

cantest can1 &
cantest can0 5A1#11.2233.44556677.88
```

---

## Ethernet

- **gmac0**: `ethernet@fe2a0000` → PHY0 → J14 (native on Starter Kit) → U5  
- **gmac1**: `ethernet@fe010000` → PHY1 → J19 (via expansion board) → U6

### gmac0

```bash
udhcpc -i eth1
```

To calculate `tx_delay` and `rx_delay`:

```bash
cd /sys/devices/platform/fe2a0000.ethernet/
echo 1000 > phy_lb_scan
```

### gmac1

```bash
udhcpc -i eth0
```

To calculate `tx_delay` and `rx_delay`:

```bash
cd /sys/devices/platform/fe010000.ethernet/
echo 1000 > phy_lb_scan
```

---

## VPU

```bash
export XDG_RUNTIME_DIR=/var/run/user/1000/
gst-play-1.0 /mnt/bbb_sunflower_1080p_60fps_stereo_arcd.mp4 --videosink=waylandsink
```

---

## PCAL6416

```bash
gpioinfo
```

Sample output:

```
gpiochip5 - 16 lines:
  line 14: "GMAC0_RSTn"     input
  line 15: "GMAC1_RSTn"     output
```

---

## GPIOs

| Bank | Offset |
|------|--------|
| A    | 0      |
| B    | 8      |
| C    | 16     |
| D    | 24     |

Error message:

```bash
rockchip-pinctrl pinctrl: could not request pin 117 (gpio3-21) from group gmac1m0-miim on device rockchip-pinctrl
```

This refers to:

**gpio3-21 = Bank C, Pin 5 (`RK_PC5`)**