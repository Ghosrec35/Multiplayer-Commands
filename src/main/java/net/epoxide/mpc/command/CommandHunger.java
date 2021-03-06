package net.epoxide.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandHunger extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "hunger";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.hunger.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            if(params.length == 1)
            {
                player.getFoodStats().setFoodLevel(Integer.parseInt(params[0]));
                sender.addChatMessage(new ChatComponentTranslation("commands.hunger.success", params[0]));
            }
            else
            {
                player.getFoodStats().setFoodLevel(20);
                sender.addChatMessage(new ChatComponentTranslation("commands.hunger.success", String.valueOf(20)));
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
