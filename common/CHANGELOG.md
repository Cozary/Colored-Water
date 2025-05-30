# Changelog 1.21.1 Port

### Added

- Particles for glow variants.
- Multiloader.
- DataGen.

### Removed

- FoilBucketItem subclass of BucketItem that has DataComponents.ENCHANTMENT_GLINT_OVERRIDE. This caused issues with
  other mods like FramedBlocks.

### Fixed

- Right-clicking a bucket with a bucket on an empty cauldron would give you a bucket of blue water.