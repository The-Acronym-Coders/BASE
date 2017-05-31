package com.teamacronymcoders.base.client.models.generator;

import com.teamacronymcoders.base.client.models.generator.generatedmodel.IGeneratedModel;

import java.util.List;

public interface IHasGeneratedModel {
    List<IGeneratedModel> getGeneratedModels();
}
