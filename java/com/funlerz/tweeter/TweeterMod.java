package com.funlerz.tweeter;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, acceptedMinecraftVersions = ModInfo.MCVERSION)
public class TweeterMod {
	
	public static Tweeter tweeter;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		try {
			tweeter = new Tweeter();
		} catch (Exception e) {
			System.out.println("Erroring in the try/catch in TweeterMod's FMLPreInitializationEvent");
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager manager = (ServerCommandManager) command;
		manager.registerCommand(new TweetCommand());
	}
	
}
