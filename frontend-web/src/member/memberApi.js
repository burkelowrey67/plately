import { api } from "../api/axiosConfig";

export async function createMember(member) {
    const response = await api.post("/household/members", member);
    return response.data;
}

export async function editMember(id, member) {
    const response = await api.put(`household/members/${id}`, member);
    return response.data;
}

export async function getMember(id) {
    const response = await api.get(`household/members/${id}`);
    return response.data;
}

export async function getMembers() {
    const response = await api.get("household/members");
    return response.data.members;
}