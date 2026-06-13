package com.flatironstudios.plately.ingredient;

class IngredientUnitParser {

    public static IngredientUnit parse(String unit) {

        if (unit.endsWith("s") && unit.length() > 1) {
            unit = unit.substring(0, unit.length() - 1);
        }

        if (unit.equalsIgnoreCase("tsp") || 
            unit.equalsIgnoreCase("teaspoon")) {
            return IngredientUnit.TSP;
        }

        if (unit.equalsIgnoreCase("tbsp") || 
            unit.equalsIgnoreCase("tablespoon")) {
            return IngredientUnit.TBSP;
        }

        if (unit.equalsIgnoreCase("cup")) {
            return IngredientUnit.CUP;
        }

        if (unit.equalsIgnoreCase("ml") || 
            unit.equalsIgnoreCase("milliliter")) {
            return IngredientUnit.ML;
        }

        if (unit.equalsIgnoreCase("l") || 
            unit.equalsIgnoreCase("liter")) {
            return IngredientUnit.LITER;
        }

        if (unit.equalsIgnoreCase("g") || 
            unit.equalsIgnoreCase("gram")) {
            return IngredientUnit.GRAM;
        }

        if (unit.equalsIgnoreCase("mg") || 
            unit.equalsIgnoreCase("milligram")) {
            return IngredientUnit.MG;
        }

        if (unit.equalsIgnoreCase("oz") ||
            unit.equalsIgnoreCase("ounce")) {
            return IngredientUnit.OZ;
        }

        if (unit.equalsIgnoreCase("lb") ||
            unit.equalsIgnoreCase("pound")) {
            return IngredientUnit.LB;
        }

        if (unit.equalsIgnoreCase("count")) {
            return IngredientUnit.COUNT;
        }

        if (unit.equalsIgnoreCase("serving")) {
            return IngredientUnit.SERVINGS;
        }

        return null;
    }
}
