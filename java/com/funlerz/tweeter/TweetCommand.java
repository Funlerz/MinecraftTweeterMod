package com.funlerz.tweeter;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class TweetCommand extends CommandBase {
	
	private static String name = "tweet";
	private static String usage = "/tweet \"message\" - Sends \"message\" as a tweet (you must authenticate your account first).";
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return usage;
	}
	
	@Override
	public void execute(ICommandSender sender, String[] args) {
		if (sender instanceof EntityPlayer) {
			ChatComponentText message = new ChatComponentText("You are tweeting!");
			((EntityPlayer) sender).addChatComponentMessage(message);
		}
	}
	
}
