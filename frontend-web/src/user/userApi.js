import api from "../api/axiosConfig"

export async function updateName(name) {
    const response = await api.patch("/user/name");
    return response.data;
}