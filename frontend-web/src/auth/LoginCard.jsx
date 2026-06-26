import { useState } from "react";
import { Card } from "../components/card";
import { getCurrentUser, login } from "./authApi";
import { Button } from "../components/button";
import { Form } from "../components/form";
import { useNavigate } from "react-router-dom";
import "../style/utilities.css"
import { useAuth } from "./AuthContext";

export default function LoginCard() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();
    const { user, setUser } = useAuth();

    async function auth(event) {
        event.preventDefault();

        try {
            const response = await login(email, password);
            setUser(response);
            navigate("/dashboard");
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
        <Card className="max-width-md full-width">
            <h2>Login</h2>

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

                <Button type="submit" className="full-width">
                    login
                </Button>

            </Form>
        </Card>
    )
}