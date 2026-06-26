import { api } from "../api/axiosConfig"

export async function updateName(name) {
    await api.patch("/user/name", {
        name
    });
}