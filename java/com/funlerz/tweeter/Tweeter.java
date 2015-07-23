package com.funlerz.tweeter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Tweeter {
	
	Twitter twitter;
	RequestToken requestToken;
	AccessToken accessToken;
	
	public Tweeter() throws Exception {
		twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer("*****************", "**************************************************");
		requestToken = twitter.getOAuthRequestToken();
		accessToken = null;
	}
	
	public void tweet(EntityPlayer sender, String[] args){
		if (accessToken == null) { // We need to authenticate.
			if (args != null) { // The user entered what we hope is the PIN.
				try {
					accessToken = twitter.getOAuthAccessToken(requestToken, args[0]);
					twitter.setOAuthAccessToken(accessToken);
					ChatComponentText message = new ChatComponentText("You have authorized Tweeter, you can now use the /tweet command properly!");
					sender.addChatComponentMessage(message);
				} catch (Exception e) {
					System.out.println("Failed to get OAuth Access Token.");
					ChatComponentText message = new ChatComponentText("Authorization failed, have you entered the PIN correctly?");
					sender.addChatComponentMessage(message);
				}
			} else { // The user still needs to get a PIN.
				String authenticateURL = requestToken.getAuthenticationURL();
				ClickEvent authenticateTweeterClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, authenticateURL);
				ChatStyle authenticateTweeterChatStyle = new ChatStyle().setChatClickEvent(authenticateTweeterClickEvent);
				ChatComponentText authenticateMessage = new ChatComponentText("Click here to authenticate Tweeter.");
				authenticateMessage.setChatStyle(authenticateTweeterChatStyle);
				sender.addChatComponentMessage(authenticateMessage);
				
				ChatComponentText explainMessage = new ChatComponentText("Once you have authorized this mod and copied the PIN, use the /tweet command followed by the PIN.");
				sender.addChatComponentMessage(explainMessage);
			}
		} else { // We are able to tweet!
			if (args == null) {
				ChatComponentText message = new ChatComponentText("You must supply a message to tweet (surround the message with \"double quotation marks\" please).");
				sender.addChatComponentMessage(message);
			} else {
				String tweet = "";
				for (int i = 0; i < args.length; i++) {
					tweet = tweet + args[i];
					tweet = tweet + " ";
				}
				tweet = tweet.trim();
				
				if (tweet.length() <= 140) {
					try {
						Status status = twitter.updateStatus(tweet);
						ChatComponentText message = new ChatComponentText("You successfully tweeted \"" + tweet + "\"");
						sender.addChatComponentMessage(message);
					} catch (Exception e) {
						ChatComponentText message = new ChatComponentText("Your tweet was unable to be sent.");
						sender.addChatComponentMessage(message);
					}
				} else { // The tweet is too long!
					ChatComponentText message = new ChatComponentText("Your tweet is too long!");
					sender.addChatComponentMessage(message);
				}
			}
		}
	}
	
}
