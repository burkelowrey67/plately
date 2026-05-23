import api from "./axiosConfig";

export const getMeal = () => api.get("/api/meals");

export const createMeal = (mealData) => api.post("/api/meals", mealData);

export const deleteMeal = (mealId) => api.delete(`/api/meals/${mealId}`);