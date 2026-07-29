# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0-alpha.3] - 2026-07-29

> [!WARNING]
> This is an **experimental alpha build**. Due to the migration to a single unified fluid, this version is **incompatible with worlds or saves from previous versions**.
> It's also likely to be prone to crashes. Use the Alpha versions at your own risk.

### Fixed
- Fixed bucket dye recipe color calculation when starting from a normal water bucket to output exact base dye colors instead of blending with default water blue.

### Changed
- Cleaned up inline fully qualified class names and consolidated imports across common, fabric, forge, and neoforge modules.

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