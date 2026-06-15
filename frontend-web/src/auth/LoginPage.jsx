import LoginCard from "./LoginCard";
import { Link } from "react-router-dom";

export default function LoginPage() {
    return (
        <div>
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