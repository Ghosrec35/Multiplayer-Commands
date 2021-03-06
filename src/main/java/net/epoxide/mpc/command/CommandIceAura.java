package net.epoxide.mpc.command;

import net.epoxide.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandIceAura extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "iceaura";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.iceaura.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            ExtendedPlayerData data = (ExtendedPlayerData)player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            //data.setIceAuraActivated(!data.isIceAuraActivated());
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
