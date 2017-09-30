TARGETS = console-setup resolvconf mountkernfs.sh ufw plymouth-log screen-cleanup x11-common hostname.sh apparmor udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh lvm2 hwclock.sh networking urandom mountdevsubfs.sh open-iscsi iscsid checkfs.sh mountall-bootclean.sh mountall.sh bootmisc.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountnfs.sh procps kmod
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
hwclock.sh: mountdevsubfs.sh
networking: resolvconf mountkernfs.sh urandom procps
urandom: hwclock.sh
mountdevsubfs.sh: mountkernfs.sh udev
open-iscsi: networking iscsid
iscsid: networking
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
bootmisc.sh: mountall-bootclean.sh udev checkroot-bootclean.sh mountnfs-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
procps: mountkernfs.sh udev
kmod: checkroot.sh
