package com.funlerz.tweeter;

import twitter4j.TwitterException;
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
			if (args.length > 0) {
				TweeterMod.tweeter.tweet((EntityPlayer) sender, args);
			} else {
				TweeterMod.tweeter.tweet((EntityPlayer) sender, null);
			}
		}
	}
	
}
