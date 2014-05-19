package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.MultiplayerCommands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandSeeInventory extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "seeinv";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.seeinv.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length == 1)
            {
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                EntityPlayer commPlayer = getCommandSenderAsPlayer(sender);
                EntityPlayer player = server.getConfigurationManager().getPlayerForUsername(params[0]);
                commPlayer.getEntityData().setString("PlayerViewName", params[0]);
                commPlayer.openGui(MultiplayerCommands.instance, 0, player.worldObj, (int)commPlayer.posX, (int)commPlayer.posY, (int)commPlayer.posZ);
            }
        }
    }
}
