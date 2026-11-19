package com.cozary.colored_water;

import com.cozary.colored_water.init.RegistrationProvider;
import com.cozary.colored_water.init.RegistryObject;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class ForgeRegistrationFactory implements RegistrationProvider.Factory {

    @Override
    public <T> RegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        final var containerOptional = ModList.getModContainerById(modId);
        if (containerOptional.isEmpty()) {
            throw new NullPointerException("Cannot find mod container for id " + modId);
        }
        final var modContainer = containerOptional.get();
        if (modContainer instanceof FMLModContainer fmlModContainer) {
            final var register = DeferredRegister.create(resourceKey, modId);
            register.register(fmlModContainer.getModBusGroup());
            return new Provider<>(modId, register);
        } else {
            throw new ClassCastException("The container of the mod " + modId + " is not a FML one!");
        }
    }

    private static class Provider<T> implements RegistrationProvider<T> {
        private final String modId;
        private final DeferredRegister<T> registry;

        private final Set<RegistryObject<T>> entries = new HashSet<>();
        private final Set<RegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

        private Provider(String modId, DeferredRegister<T> registry) {
            this.modId = modId;
            this.registry = registry;
        }

        @Override
        public String getModId() {
            return modId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> RegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            final var registeredObject = registry.<I>register(name, supplier);
            final var registryObject = new RegistryObject<I>() {

                @Override
                public ResourceKey<I> getResourceKey() {
                    return registeredObject.getKey();
                }

                @Override
                public Identifier getId() {
                    return registeredObject.getId();
                }

                @Override
                public I get() {
                    return registeredObject.get();
                }

                @Override
                public Holder<I> asHolder() {
                    return registeredObject.getHolder().orElseThrow();
                }
            };
            entries.add((RegistryObject<T>) registryObject);
            return registryObject;
        }

        @Override
        public Set<RegistryObject<T>> getEntries() {
            return entriesView;
        }
    }
}
