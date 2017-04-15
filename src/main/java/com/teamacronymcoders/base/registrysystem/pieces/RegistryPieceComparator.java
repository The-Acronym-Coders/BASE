package com.teamacronymcoders.base.registrysystem.pieces;

import net.minecraftforge.fml.common.eventhandler.EventPriority;

import java.util.Comparator;

public class RegistryPieceComparator implements Comparator<IRegistryPiece> {
    @Override
    public int compare(IRegistryPiece registryPiece1, IRegistryPiece registryPiece2) {
        RegistryPiece annotation1 = registryPiece1.getClass().getAnnotation(RegistryPiece.class);
        RegistryPiece annotation2 = registryPiece2.getClass().getAnnotation(RegistryPiece.class);

        EventPriority eventPriority1 = annotation1.priority();
        EventPriority eventPriority2 = annotation2.priority();

        return Integer.compare(eventPriority1.ordinal(), eventPriority2.ordinal());
    }
}
