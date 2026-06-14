import { useState } from "react";
import { Card } from "../components/card";
import { login } from "./authApi";
import { Button } from "../components/button";

export default function LoginCard() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    async function auth(event) {
        event.preventDefault();

        try {
            const response = await login(email, password);
        } 
        
        catch(e) {
            if (e.response?.status === 401) {
                setError("Invalid credentials");
            } else {
                setError("Something went wrong");
            }
        }
    }

    return (
        <Card>
            <h2>Login</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <form onSubmit={auth}>

                <div>
                    <label>Email</label>
                    <input 
                        type="email"
                        value={email}
                        onChange={(e) => 
                            setEmail(e.target.value)
                        }
                    />
                </div>

                <div>
                    <label>Password</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) =>
                            setPassword(e.target.value)
                        }
                    />
                </div>

                <Button type="submit">
                    Login
                </Button>
            </form>
        </Card>
    )
}