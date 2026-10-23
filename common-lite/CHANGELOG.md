# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.1.0] - 2026-10-24

### Added
- Added vanilla fluid tags support for all still and flowing colored water variants:
  - `#minecraft:supports_lily_pad`.
  - `#minecraft:supports_frogspawn`.
  - `#minecraft:bubble_column_can_occupy`.
  - `#minecraft:entity_floatable`.
  - `#minecraft:supports_sugar_cane_adjacently`.
  - `#minecraft:axolotl_tries_to_find`, `#minecraft:dolphin_tries_to_find`, and `#minecraft:frog_tries_to_find_land_near`.

### Fixed
- Fixed lily pads unable to be placed on colored water.
- Fixed duplicate recipe identifier in datagen for condensed orange water bucket.

## [2.0.0] - 2026-10-18

### Added
- Added colored water luminous cauldrons emitting light and sparkle particles.
- Added waterlogged blocks compatibility.
- Added bubble column mechanics with Soul Sand and Magma Blocks.
- Added Pointed Dripstone mechanics: colored water drips into cauldrons.
- Added obsidian, cobblestone, and stone generation when colored water interacts with lava.
- Added dispenser mechanics: place and retrieve colored water using buckets.
- Added Frost Walker and Riptide enchantment compatibility.
- Added aquatic creature breathing.
- Added underwater fog and screen overlay rendering.
- Added colored water particle effects (splash, bubble, wake, underwater, dripping, and luminous sparkles).