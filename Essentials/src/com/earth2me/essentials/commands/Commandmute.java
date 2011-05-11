package com.earth2me.essentials.commands;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import com.earth2me.essentials.User;
import com.earth2me.essentials.Util;


public class Commandmute extends EssentialsCommand
{
	public Commandmute()
	{
		super("mute");
	}

	@Override
	public void run(Server server, CommandSender sender, String commandLabel, String[] args) throws Exception
	{
		if (args.length < 1)
		{
			throw new NotEnoughArgumentsException();
		}

		User p = getPlayer(server, args, 0);
		long muteTimestamp = 0;
		if (args.length > 1) {
			String time = getFinalArg(args, 1);
			muteTimestamp = Util.parseDateDiff(time, true);
		}
		p.setMuteTimeout(muteTimestamp);
		charge(sender);
		
		
		sender.sendMessage(Util.format("mutedPlayer", p.getDisplayName(), (p.toggleMuted() ? Util.i18n("muted") : Util.i18n("unmuted")), (muteTimestamp > 0 ? Util.i18n("for") + Util.formatDateDiff(muteTimestamp) : "")));
	}
}
