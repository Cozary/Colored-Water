# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.1.0] - 2026-10-24

### Added
- Added vanilla fluid tags support:
  - `#minecraft:supports_lily_pad`.
  - `#minecraft:supports_frogspawn`.
  - `#minecraft:bubble_column_can_occupy`.
  - `#minecraft:entity_floatable`.
  - `#minecraft:supports_sugar_cane_adjacently`.
  - `#minecraft:axolotl_tries_to_find`, `#minecraft:dolphin_tries_to_find`, and `#minecraft:frog_tries_to_find_land_near`.

### Fixed
- Fixed lily pads unable to be placed on colored water.

## [2.0.0] - 2026-10-18

> [!WARNING]
> Due to the migration to a single unified fluid, version 2.0.0 is **incompatible with worlds or saves from versions prior to 2.0.0**.
> Always make backups before upgrading existing worlds.

### Added
- Added Dripping colored water particles and luminous drip sparkle particles in client level.

### Changed
- Optimized colored water particles.

### Fixed
- Fixed fishing particles.
- Fixed `isInWater` and `isInWaterOrBubbleColumn` entity checks returning false inside colored water and cauldrons.
- Fixed dispensers with empty buckets being unable to pick up colored water from waterlogged blocks and bubble columns.
- Fixed bubble particles and drip particles.

## [2.0.0-beta.3] - 2026-09-27

> [!WARNING]
> This is a **beta build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from versions prior to 2.0.0**.
> Use with appropriate backups.

### Added
- Added underwater fog rendering that matches the colored water color and light level.

### Changed
- Improved fluid mixing propagation and smooth color transitions in flowing water.
- Improved cauldron color blending and Pointed Dripstone dripping mechanics.
- Optimized colored water particles.

### Fixed
- Fixed Trident with Riptide enchantment not activating inside colored water.
- Fixed fish and aquatic creatures drowning inside colored water.
- Fixed water splash particles and bubble particles.
- Fixed bubble columns and waterlogged blocks rendering with default vanilla water.

### Removed
- Cleaned up obsolete particle textures and unused legacy code.

## [2.0.0-beta.2] - 2026-09-09

> [!WARNING]
> This is a **beta build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from versions prior to 2.0.0**.
> Use with appropriate backups.

### Added
- Added full localization for bucket tooltips (`en_us`, `es_es`).
- Frost Walker compatibility: colored water now properly retains its color and properties when frozen into frosted ice and thawed.

### Changed
- Flowing colored water now transitions smoothly between colors and light levels instead of changing abruptly.
- Improved overall performance when in water, rain, and around flowing fluids.
- Optimized lighting updates.
- Cleaned and updated language files.

### Fixed
- Fixed colored water losing its color or glow when breaking or melting ice.
- Fixed picking up colored water with a bucket from waterlogged blocks and bubble columns resetting its properties.
- Fixed cauldrons not immediately updating their light level when color or luminosity changed.

### Removed
- Cleaned up obsolete files and unused legacy code.

## [2.0.0-beta.1] - 2026-08-21

> [!WARNING]
> This is a **beta build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from versions prior to 2.0.0**.
> Use with appropriate backups.

### Added
- Added Bubble Column mechanics for colored water with Soul Sand and Magma Block.

### Fixed
- Fixed an issue where falling colored water would generate source blocks instead of flowing water blocks.
- Fixed outdated cauldron tag entries in cauldrons tag. (Old cauldrons)

## [2.0.0-alpha.7] - 2026-08-19

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Added
- Added colored water particles for all vanilla particle types.

## [2.0.0-alpha.6] - 2026-08-17

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Added
- Added Waterlog. Blocks like slabs, stairs, and fences can now be waterlogged with colored water.
- Added Frost Walker enchantment compatibility with colored water.
- Added compatibility with Fish, axolotls, and other aquatic entities can now breathe and survive inside colored water.

## [2.0.0-alpha.5] - 2026-08-06

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Added
- Added obsidian, cobblestone, and stone generation mechanics when interacting with lava.
- Re-implemented sparkle particles for luminous colored water and cauldrons, scaling emission rate dynamically with light level.
- Added Riptide trident enchantment compatibility in colored water blocks and cauldrons.
- Added Pointed Dripstone mechanic. Now colored water drip into cauldrons.
- Added Dispenser mechanic. Place and pickup fluid.

## [2.0.0-alpha.4] - 2026-08-03

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Added
- Added cauldron behaviors for dyeable items (Leather Armor, Wolf Armor, Leather Horse Armor), Shulker Boxes, and Banners.
- Added color mixing logic when dyeing already-dyed items inside a colored water cauldron.

## [2.0.0-alpha.3] - 2026-07-29

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Added
- Cauldrons now support colored water! Fill a cauldron with a Colored Water Bucket to store and mix any color directly inside it.
- The liquid inside a cauldron changes color dynamically based on what you pour in, including opacity, glow, and condensed variants.

### Fixed
- Fixed an issue where crafting a dyed bucket starting from a plain water bucket would produce a slightly off color instead of the exact dye color.

## [2.0.0-alpha.2] - 2026-07-23

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Added
- Single unified colored water fluid supporting dynamic ARGB tinting, custom opacity, and light emission.
- Dynamic shapeless crafting recipes: combine Water Buckets or existing Colored Water Buckets with Dyes, Redstone (Condensed modifier), and Glowstone Dust (Luminous modifier) to craft, recolor, and upgrade buckets.
- Informative bucket tooltips displaying fluid type (Normal, Condense, Luminous, Luminous Condense), opacity percentage, luminosity, and hex color preview.

### Changed
- Updated `/coloredwater` command syntax to `/coloredwater <type> <hexcolor> [luminosity] [translucency]`.

### Removed
- Removed legacy individual fluid block and bucket items in favor of the new single unified fluid.

## [2.0.0-alpha] - 2026-07-16

### Added
- Introduced a new fluid type that allows for water in any color like dyeable leather armor.
- Colored Water Bucket: A new item that holds specific custom colors of water.
- New Command: Added `/coloredwater` command to manage and obtain custom water buckets.