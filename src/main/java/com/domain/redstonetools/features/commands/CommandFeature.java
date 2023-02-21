package com.domain.redstonetools.features.commands;

import com.domain.redstonetools.features.AbstractFeature;
import com.domain.redstonetools.utils.CommandUtils;
import com.domain.redstonetools.utils.ReflectionUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;

public abstract class CommandFeature extends AbstractFeature {
    @Override
    protected void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        var info = ReflectionUtils.getFeatureInfo(getClass());
        var arguments = ReflectionUtils.getArguments(getClass());

        CommandUtils.register(
                info.command(),
                arguments,
                context -> {
                    for (var argument : arguments) {
                        argument.acquireValue(context);
                    }

                    return execute(context.getSource());
                },
                dispatcher,
                dedicated);
    }

    protected abstract int execute(ServerCommandSource source) throws CommandSyntaxException;
}
