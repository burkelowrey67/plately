import "./Onboard.css"
import "../style/utilities.css"

import { Card } from "../components/card";
import { Form } from "../components/form";
import { Button } from "../components/button";
import { completeWelcome } from "./onboardApi";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";


export default function WelcomeCard() {
    const navigate = useNavigate();
    const { refreshUser } = useAuth();

    async function onClick() {
        console.log("Test");
        try {
            await completeWelcome();
            refreshUser();
            navigate("/onboard/name");
        }

        catch (e) {
            console.log(e.message);
        }
    }


    return (
            <Card className="welcome-card">
                <h1 className="welcome-title">Welcome to Plately</h1>

                <div className="group-gap-md">
                    
                    <p className="welcome-slogan">
                        Personalized meal planning and grocery lists for your household.
                    </p>

                    <p className="welcome-text">
                        Let's start by learning a little about you so we can tailor your experience.
                    </p>
                </div>

                <Button
                    className="welcome-button"
                    onClick={onClick}
                >
                    Get Started
                </Button>
            </Card>
    );
}