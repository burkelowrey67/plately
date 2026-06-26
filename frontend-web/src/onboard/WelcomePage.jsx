import { Button } from "../components/button";
import { useNavigate } from "react-router-dom";
import "../style/layouts.css";
import "./Onboard.css"
import { updateName } from "../user/userApi";
import { completeWelcome } from "./onboardApi";
import WelcomeCard from "./WelcomeCard";

export default function OnboardWelcomePage() {
    return (
        <div className="centered-page"> 
            <WelcomeCard/>
        </div>
    );  
}