import LoginCard from "./LoginCard";
import { Link } from "react-router-dom";
import "../style/layouts.css"

export default function LoginPage() {
    return (
        <div className="centered-page">
            <h1>Plately</h1>
            <LoginCard/>

            <p>
                Not a registered user?{" "}
                <Link to="/register">
                    Register here
                </Link>
            </p>
        </div>
    );
}