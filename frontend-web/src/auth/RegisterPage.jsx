import RegisterCard from "./RegisterCard"
import { Link } from "react-router-dom";

export default function RegisterPage() {
    return (
        <div>
            <h1>Plately</h1>
            <RegisterCard/>

            <p>
                Already a registered user?{" "}
                <Link to="/">
                    Login here
                </Link>
            </p>
        </div>
    );
}