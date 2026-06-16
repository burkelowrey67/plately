import { useState } from "react";
import { Card } from "../components/card";
import { login } from "./authApi";
import { Button } from "../components/button";
import "./AuthCard.css"

export default function LoginCard() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    async function auth(event) {
        event.preventDefault();

        try {
            console.log(email);
            const response = await login(email, password);
        } 
        
        catch(e) {
            if (e.response?.status === 404) {
                setError("This email address is not registered");
            }
            else if (e.response?.status === 401) {
                setError("Invalid credentials");
            } else {
                setError("Something went wrong");
            }
        }
    }

    return (
        <Card className="auth-card">
            <h2>Login</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <form onSubmit={auth} className="auth-form">

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

                <Button type="submit">
                    Login
                </Button>

            </form>
        </Card>
    )
}