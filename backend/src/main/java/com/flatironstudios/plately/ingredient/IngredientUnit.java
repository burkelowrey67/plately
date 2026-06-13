package com.flatironstudios.plately.ingredient;

public enum IngredientUnit {
    TSP(UnitType.VOLUME, 1.0),
    TBSP(UnitType.VOLUME, 3.0),
    CUP(UnitType.VOLUME, 48.0),
    ML(UnitType.VOLUME, 0.202884),
    LITER(UnitType.VOLUME, 0.004928),
    GRAM(UnitType.MASS, 1.0),
    MG(UnitType.MASS, 100.0),
    OZ(UnitType.MASS, 28.3495),
    LB(UnitType.MASS, 453.592),
    COUNT(UnitType.COUNT, 1.0),
    SERVINGS(UnitType.SERVINGS, 1.0);

    public final UnitType type;
    public final double toBaseUnit;

    IngredientUnit(UnitType type, double toBaseUnit) {
        this.type = type;
        this.toBaseUnit = toBaseUnit;
    }
}
