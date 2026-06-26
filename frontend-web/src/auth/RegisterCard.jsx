import { useState } from "react";
import { Card } from "../components/card";
import { register } from "./authApi";
import { Button } from "../components/button";
import { useNavigate } from "react-router-dom";
import { Form } from "../components/form";
import "../style/utilities.css"
import { useAuth } from "./AuthContext";

export default function RegisterCard() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [retype, setRetype] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();
    const { setUser } = useAuth();

    async function auth(event) {
        event.preventDefault();

        if (email.trim() === "") {
            setError("Please enter an email");
            return;
        }

        if (password.trim() === "") {
            setError("Please enter a password");
            return;
        }

        if (password !== retype) {
            setError("Passwords do not match");
            return; 
        }

        if (!email.includes("@")) {
            setError("Invalid email");
            return;
        }

        if (password.length < 8) {
            setError("Password must be at least 8 characters");
            return;
        }

        try {
            const response = await register(
                email, password
            );

            setUser(response);
            navigate("/login");
        }

        catch (e) {
            if (e.response?.status === 409) {
                setError("This email is already registered");
            }

            else {
                setError("Something went wrong");
            }
            
            console.log(e.message);
        }
    }

    return (
        <Card className="max-width-md full-width">
            <h2>Register</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <Form onSubmit={auth}>

                <div className="form-group">
                    <label>Email address</label>
                    <input
                        type="email"
                        placeholder="you@example.com"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input
                        type="password"
                        placeholder="Enter password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <input
                        type="password"
                        placeholder="Re-type password"
                        value={retype}
                        onChange={(e) => setRetype(e.target.value)}
                    />
                </div>

                <Button type="submit" className="full-width">
                    register
                </Button>

            </Form>
    </Card>
    );
}