# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0-beta.1] - 2026-09-27

> [!NOTE]
> This is a **beta build** of Colored Water Lite.
> This version introduces major code optimizations, unified Java fluid architectures, full vanilla parity integrations, and colored water particle systems while preserving 100% compatibility with classic registry IDs and worlds.

- Added full waterlogging support for colored water:
  - Waterloggable blocks (slabs, stairs, fences, trapdoors, etc.) can now be waterlogged with any colored water variant using colored water buckets or dispensers.
  - Picking up colored water from a waterlogged block with an empty bucket returns the exact matching colored water bucket.
  - Placing waterloggable blocks into existing colored water source blocks preserves the fluid and waterlogs the block.
  - Preserved fluid identity, client visual rendering, underwater fog, ambient splash effects, and luminous light emission for all waterlogged colored water blocks across world saves and chunk reloads.
- Added custom colored water particles for all vanilla particle interactions:
  - Splashing particles (`splash`) and underwater bubbles (`bubble`) when entities interact with colored water.
  - Bubble column particles (`bubble_column_up`, `current_down`) for Soul Sand and Magma Block bubble columns in colored water.
  - Rain splash particles (`rain`) when precipitation hits colored water surfaces.
  - Fishing wake and splash particles (`fishing`) around bobbers in colored water.
  - Ambient underwater particles (`underwater`) matching the specific water tint.
  - Ceiling dripping water particles (`dripping_water`, `falling_water`).
  - Pointed Dripstone colored water hang and fall particles (`dripping_dripstone_water`, `falling_dripstone_water`).
- Added underwater colored fog rendering matching fluid tint and light level (Fabric, NeoForge, and Forge).
- Added Dispenser mechanics for colored water:
  - Placing colored water fluids from all 64 buckets.
  - Empty bucket pickup returning the matching colored water bucket.
- Added Frost Walker enchantment compatibility, freezing colored water into frosted ice.
- Added Bubble Column mechanics for colored water with Soul Sand and Magma Block.
- Added Obsidian and Cobblestone generation mechanics when interacting with lava.
- Added colored water cauldron recognition for entity water status (extinguishing fire and Riptide trident charging).

### Changed
- Unified 64 separate fluid classes in `common-lite` into a single parameterized `ColorWaterFluid` architecture.
- Unified 64 separate mixin classes in `neoforge-lite` and `forge-lite` into a single `BaseColorWaterMixin`.
- Optimized particle performance: implemented static instance caching in `SparkleParticleOptions` and `ColorParticleOptions` to eliminate per-tick allocations.
- Updated project and loader versions to `2.0.0-beta.1`.

### Fixed
- Fixed crash during mod construction on NeoForge by deferring dispenser behavior registration to `postInit` during `FMLCommonSetupEvent`.
- Cleaned up all inline qualified imports across loaders.