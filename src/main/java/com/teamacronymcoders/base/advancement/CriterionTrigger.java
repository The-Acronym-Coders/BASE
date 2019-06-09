package com.teamacronymcoders.base.advancement;

import com.google.common.collect.Maps;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.function.Function;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class CriterionTrigger<T extends CriterionListeners<U>, U extends ICriterionInstance> implements ICriterionTrigger<U> {
    private final ResourceLocation id;
    private final Function<PlayerAdvancements, T> createNew;
    private final Map<PlayerAdvancements, T> listeners = Maps.newHashMap();

    protected CriterionTrigger(ResourceLocation id, Function<PlayerAdvancements, T> createNew) {
        this.id = id;
        this.createNew = createNew;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancements, ICriterionTrigger.Listener<U> listener) {
        T listeners = this.listeners.get(playerAdvancements);
        if (listeners == null) {
            listeners = createNew.apply(playerAdvancements);
            this.listeners.put(playerAdvancements, listeners);
        }
        listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancements, Listener<U> listener) {
        T listeners = this.listeners.get(playerAdvancements);

        if (listeners != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                this.listeners.remove(playerAdvancements);
            }
        }
    }

    @Nullable
    public T getListeners(PlayerAdvancements playerAdvancements) {
        return this.listeners.get(playerAdvancements);
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancements) {
        this.listeners.remove(playerAdvancements);
    }
}
