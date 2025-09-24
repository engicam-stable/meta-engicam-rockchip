# How to Create a Distrobox Image for Yocto Scarthgap Build

1. **Install Distrobox**  
   Please refer to the [installation chapter](https://distrobox.it/#installation).

2. **Create an Ubuntu 24 image**:  

   ```bash
   distrobox-create --name yocto_ubuntu24_scarthgap --image docker.io/library/ubuntu:24.04
   distrobox enter yocto_ubuntu24_scarthgap
   ```

3. **Install Yocto required packages**:  

   ```bash
   sudo apt-get update
   sudo apt-get install build-essential chrpath cpio debianutils diffstat file gawk gcc git iputils-ping \
       libacl1 liblz4-tool locales python3 python3-git python3-jinja2 python3-pexpect python3-pip \
       python3-subunit socat texinfo unzip wget xz-utils zstd efitools vim
   ```

Now you are ready to prepare the [Yocto sources environment](../README.md#prepare-the-environment).

You can remove the image with the following command:  

```bash
distrobox-rm yocto_ubuntu24_scarthgap
```
