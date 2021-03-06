package net.epoxide.mpc.command;

import net.epoxide.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;

public class CommandBack extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "back";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.back.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            ExtendedPlayerData data = (ExtendedPlayerData)player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            player.setPositionAndUpdate(data.getBackXCoordinate(), data.getBackYCoordinate(), data.getBackZCoordinate());
            NBTTagCompound tag = new NBTTagCompound();
            data.saveNBTData(tag);
            NBTTagCompound innerTag = (NBTTagCompound) tag.getTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            innerTag.setDouble("LastPositionX", player.posX);
            innerTag.setDouble("LastPositionY", player.posY);
            innerTag.setDouble("LastPositionZ", player.posZ);
            tag.setTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT, innerTag);
            data.loadNBTData(tag);
            sender.addChatMessage(new ChatComponentTranslation("commands.back.success", player.posX, player.posY, player.posZ));
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
