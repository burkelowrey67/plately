import axios from "axios"
import api from "../api/axiosConfig";

export async function login(email, password) {
    const response = await api.post("/auth/login", {
        email, password
    });

    return response.data;
}

export async function register(name, email, password) {
    const response = await api.post("/auth/register", {
        email, password, name
    });

    return response.data;
}   

export async function logout() {
    await api.post("auth/logout");
}

export async function me() {
    const response = await api.get("auth/me");
    return response.data;
}