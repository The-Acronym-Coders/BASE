package com.teamacronymcoders.base.materialsystem.partdata;

import com.google.common.collect.Lists;
import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.materialsystem.MaterialException;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.materials.Material;
import com.teamacronymcoders.base.materialsystem.parttype.PartDataPiece;

import java.util.*;
import java.util.function.Function;

public class MaterialPartData {
    private MaterialPart materialPart;
    private Map<String, String> data;
    private List<PartDataPiece> dataPieces;

    public MaterialPartData(MaterialPart materialPart, List<PartDataPiece> dataPieces) {
        this.data = new HashMap<>();
        this.dataPieces = dataPieces;
        this.materialPart = materialPart;
    }

    public String getDataPiece(String name) {
        return this.data.get(name.toLowerCase(Locale.US));
    }

    public <T> T getValue(String name, T defaultValue, Function<String, T> parser) {
        T value = defaultValue;
        try {
            String dataPiece = this.getDataPiece(name);
            if (dataPiece != null) {
                value = parser.apply(dataPiece);
            }
        } catch (NumberFormatException exception) {
            Base.instance.getLogger().warning(exception.getMessage());
        }
        return value;
    }

    public <T> List<T> getValues(String name, T[] defaultValue, Function<String, T> parser) {
        String value = this.getDataPiece(name);
        List<T> returnList = Lists.newArrayList(defaultValue);
        if (value != null) {
            String[] valueArray = value.split(",");
            List<T> actualValues = Lists.newArrayList();
            for (String string : valueArray) {
                returnList.add(parser.apply(string));
            }
            returnList = actualValues;
        }
        return returnList;
    }

    public <T> List<T> getValues(String name, T[] defaultValue, Function<String, T> parser, int expectedValues) {
        List<T> values = this.getValues(name, defaultValue, parser);
        if (values.size() != expectedValues) {
            this.materialPart.getMaterialUser().logError("Didn't find expected number of values for field " + name +
                ". Expected " + expectedValues + ". Found " + values.size() + ".");
        }
        return values;
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
                if (dataPiecesIterable.next().getName().equalsIgnoreCase(dataName)) {
                    hasDataPiece = true;
                }
            }
            if (!hasDataPiece) {
                throw new MaterialException("No data piece found for name: " + dataName);
            }
        }
    }
}
