import RegisterCard from "./RegisterCard"
import { Link } from "react-router-dom";
import "../style/layouts.css"

export default function RegisterPage() {
    return (
        <div className="centered-page">
            <h1>Plately</h1>
            <RegisterCard/>

            <p>
                Already a registered user?{" "}
                <Link to="/login">
                    Login here
                </Link>
            </p>
        </div>
    );
}