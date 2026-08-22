# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0-beta.2] - 2026-08-22

> [!WARNING]
> This is a **beta build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from versions prior to 2.0.0**.
> Use with appropriate backups.

### Added
- Bucket tooltips localization.

### Changed
- Cleaned and updated localization language files (`en_us`, `es_es`).
- Optimized entity performance when in water or rain.

### Removed
- Cleaned up unused and dead code across the mod.

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