package com.illuminatijoe.refactorycore;

import com.illuminatijoe.refactorycore.api.ReFactoryRegistries;
import com.illuminatijoe.refactorycore.api.capabilities.ILPContainer;
import com.illuminatijoe.refactorycore.api.capabilities.recipe.lookup.MapLPIngredient;
import com.illuminatijoe.refactorycore.client.ReFactoryCoreClient;
import com.illuminatijoe.refactorycore.data.ReFactorySounds;
import com.illuminatijoe.refactorycore.data.datagen.ReFactoryDatagen;
import com.illuminatijoe.refactorycore.data.materials.ReFactoryMaterials;
import com.illuminatijoe.refactorycore.data.recipes.ReFactoryCoreRecipeTypes;
import com.illuminatijoe.refactorycore.machines.ReFactoryMachines;
import com.illuminatijoe.refactorycore.machines.multiblock.MultiblockInit;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.MapIngredientTypeManager;
import com.gregtechceu.gtceu.api.sound.SoundEntry;

import com.lowdragmc.lowdraglib.Platform;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ReFactoryCore.MOD_ID)
public class ReFactoryCore {

    public static final String MOD_ID = "refactorycore";
    public static final Logger LOGGER = LogManager.getLogger();

    public ReFactoryCore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ReFactoryRegistries.REGISTRATE.registerRegistrate();
        ReFactoryDatagen.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        modEventBus.addListener(this::addMaterialRegistries);
        modEventBus.addListener(this::addMaterials);
        modEventBus.addListener(this::modifyMaterials);

        modEventBus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
        modEventBus.addGenericListener(MachineDefinition.class, this::registerMachines);
        modEventBus.addGenericListener(SoundEntry.class, this::registerSounds);

        if (Platform.isClient()) {
            ReFactoryCoreClient.init(bus);
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            MapIngredientTypeManager.registerMapIngredient(Integer.class, MapLPIngredient::convertToMapIngredient);
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {}

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    private void addMaterialRegistries(MaterialRegistryEvent event) {
        GTCEuAPI.materialManager.createRegistry(ReFactoryCore.MOD_ID);
    }

    private void addMaterials(MaterialEvent event) {
        ReFactoryMaterials.register();
    }

    private void modifyMaterials(PostMaterialEvent event) {
        // CustomMaterials.modify();
    }

    private void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        ReFactoryCoreRecipeTypes.init();
    }

    private void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        MultiblockInit.init();
        ReFactoryMachines.init();
    }

    public void registerSounds(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        ReFactorySounds.init();
    }

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ILPContainer.class);
    }
}
