package com.teamacronymcoders.base.materialsystem.materialparts;

import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.parts.PartDataPiece;

import java.util.*;

public class MaterialPartData {
    private Map<String, String> data;
    private List<PartDataPiece> dataPieces;

    public MaterialPartData(List<PartDataPiece> dataPieces) {
        this.data = new HashMap<>();
        this.dataPieces = dataPieces;
    }

    public String getDataPiece(String name) {
        return this.data.get(name.toLowerCase(Locale.US));
    }

    public boolean containsDataPiece(String name) {
        return this.data.containsKey(name.toLowerCase(Locale.US));
    }

    public void addDataValue(String name, String value) {
        data.put(name.toLowerCase(Locale.US), value);
    }

    public void validate() throws MaterialException {
        for (PartDataPiece dataPiece : dataPieces) {
            if (dataPiece.isRequired() && !data.containsKey(dataPiece.getName())) {
                throw new MaterialException("Missing required data piece: " + dataPiece.getName());
            }
        }

        for (String dataName : data.keySet()) {
            boolean hasDataPiece = false;
            Iterator<PartDataPiece> dataPiecesIterable = dataPieces.iterator();
            while(dataPiecesIterable.hasNext() && !hasDataPiece) {
                if (dataPiecesIterable.next().getName().equals(dataName)) {
                    hasDataPiece = true;
                }
            }
            if (!hasDataPiece) {
                throw new MaterialException("No data piece found for name: " + dataName);
            }
        }
    }
}
