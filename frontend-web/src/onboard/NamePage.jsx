import "../style/layouts.css"
import "./Onboard.css"

import { useState } from "react";
import { Card } from "../components/card";
import { Form } from "../components/form";
import { Button } from "../components/button";
import { useAuth } from "../auth/AuthContext";
import { useNavigate } from "react-router-dom";
import { updateName } from "./onboardApi";

export default function OnboardNamePage() {
    const [ name, setName ] = useState("");
    const [ error, setError ] = useState("");
    const { refreshUser } = useAuth();
    const navigate = useNavigate();

    async function onSubmit(event) {
        event.preventDefault();

        setName(name.trim());
        
        if (name.includes(" ")) {
            setError("First name only");
            return;
        }

        if (name.length > 12) {
            setError("Name must be 12 characters or less");
            return;
        }

        if (!/^[a-zA-Z]+$/.test(name)) {
            setError("Name must only contain letters");
            return;
        }

        try { 
            const response = await updateName(name);
            refreshUser();
            navigate("/onboard/household");
        }
        catch (e) {
            setError("Something went wrong");
        }
    }

    return (
        <div className="centered-page name-page">
            <h1>First, your name</h1>
            

            <Form onSubmit={onSubmit}>
                <input
                    type="text"
                    placeholder="Enter your name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />

                <Button type="submit" className="full-width">next</Button>
            </Form>

            
            {error && <p style={{ color: "red" }}>{error}</p>}

        </div>
    );
}