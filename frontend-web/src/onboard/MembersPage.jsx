import "../style/layouts.css"

import { useState } from "react";
import { Card } from "../components/card";
import { AddMembersCard } from "../member-edit";
import { useAuth } from "../auth/AuthContext";
import { finish } from "./onboardApi";
import { useNavigate } from "react-router-dom";

export default function MembersPage() {
    const { refreshUser } = useAuth();
    const navigate = useNavigate();

    async function handleDone() {
        await finish();
        refreshUser();
        navigate("/dashboard");
    }

    return (
        <div className="centered-page">
            <AddMembersCard onDone={handleDone}/>
        </div>
    );
}