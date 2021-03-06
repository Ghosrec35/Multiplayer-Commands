package net.epoxide.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandGod extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "god";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.god.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            boolean godMode = player.capabilities.disableDamage;
            player.capabilities.disableDamage = !godMode;
            player.sendPlayerAbilities();
            
            if(player.capabilities.disableDamage)
            {
                sender.addChatMessage(new ChatComponentTranslation("commands.god.success", translate("commands.enabled")));
            }
            else
            {
                sender.addChatMessage(new ChatComponentTranslation("commands.god.success", translate("commands.disabled")));
            }
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
