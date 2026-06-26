import axios from "axios"
import { api, apiNoCookie } from "../api/axiosConfig";

export async function login(email, password) {
    const response = await api.post("/auth/login", {
        email, password
    });

    return response.data;
}

export async function register(email, password) {
    const response = await api.post("/auth/register", {
        email, password
    });

    return response.data;
}   

export async function logout() {
    await api.post("auth/logout");
}

export async function getCurrentUser() {
    const response = await api.get("/auth/me");
    return response.data;
}