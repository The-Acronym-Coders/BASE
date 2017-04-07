package com.teamacronymcoders.base.materialsystem.commands;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.commands.CommandBase;
import com.teamacronymcoders.base.registry.config.ConfigRegistry;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommandOreDictDump extends CommandBase {
    public CommandOreDictDump() {
        super("OreDictDump");
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "";
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        String oreDictType = args[0];
        List<String> oreDictNames = Lists.newArrayList();
        oreDictNames.add("OreDictionary names for: " + oreDictType);
        int numberOfItems = 0;
        for (String name : OreDictionary.getOreNames()) {
            if (name.startsWith(oreDictType)) {
                oreDictNames.add(++numberOfItems + ". " + name);
            }
        }

        File configFolder = Base.instance.getRegistry(ConfigRegistry.class, "CONFIG").getConfigFolder();
        File oreDictFile = new File(configFolder, "oredict.txt");

        try {
            FileUtils.writeLines(oreDictFile, "UTF-8", oreDictNames, true);
        } catch (IOException e) {
            Base.instance.getLogger().getLogger().error(e);
        }
    }
}
