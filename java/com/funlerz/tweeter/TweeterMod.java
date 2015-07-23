package com.funlerz.tweeter;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, acceptedMinecraftVersions = ModInfo.MCVERSION, clientSideOnly = true)
public class TweeterMod {
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager manager = (ServerCommandManager) command;
		manager.registerCommand(new TweetCommand());
	}
	
}
