import { api } from "../api/axiosConfig";

export async function completeWelcome() {
    await api.post("/onboard/welcome");
}

export async function updateName(name) {
    await api.post("/onboard/name", {
        name
    });
}

export async function updateHousehold(name, budget) {
    await api.post("/onboard/household", {
        name, budget
    });
}

export async function addMember(member) {
    await api.post("/onboard/household/add", member);
}

export async function finish(member) {
    await api.post("/onboard/finish");
}