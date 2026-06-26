import "../style/utilities.css"

import { Card } from "../components/card";
import { useState } from "react";
import { Button } from "../components/button";
import { Form } from "../components/form";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";
import { updateHousehold } from "./onboardApi";


export default function HouseholdCard() {
    const [ error, setError ] = useState("");
    const [ name, setName ] = useState("");
    const [ budget, setBudget ] = useState("");
    const { refreshUser } = useAuth();
    const navigate = useNavigate();

    async function onSubmit() {
        event.preventDefault();

        setName(name.trim());
        setName(name.replace(/\s+/g, " "));

        if (name === "") {
            setError("Name is required");
            return;
        }

        if (name.length > 25) {
            setError("Name must be 25 characters or less");
            return;
        }

        if (budget === "") {
            setError("Budget is required");
            return
        }

        if (!/^[a-zA-Z' ]+$/.test(name)) {
            setError("Name must only contain letters");
            return;
        }

        if (budget.length > 4) {
            setError("Budget must be less than $10,000");
            return;
        }

        if (!/^[0-9]+$/.test(budget)) {
            setError("Budget must be a number");
            return;
        }

        try {
            const response = await updateHousehold(name, budget);
            refreshUser();
            navigate("/onboard/household/members");
        }

        catch(e) {
            setError("Something went wrong");
            console.log(e);
        }
    }


    return (

        <Card className="max-width-lg full-width">
            <h2>Household Information</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <Form onSubmit={onSubmit}>
                <div className="form-group">
                    <label>Name</label>
                    <input
                        type="text"
                        placeholder="Household name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label>Weekly Budget</label>

                    <div className="money-input">
                        <span>$</span>
                        <input
                            type="number"
                            min="0"
                            step="0.01"
                            value={budget}
                            onChange={(e) => setBudget(e.target.value)}
                        />
                    </div>

                </div>

                <Button type="submit" className="full-width">
                    next
                </Button>
            </Form>
        </Card>
    );
}