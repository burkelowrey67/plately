import { createContext, useContext, useState, useEffect } from "react";
import { getCurrentUser } from "./authApi";

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    const refreshUser = async () => {
        try {
            const user = await getCurrentUser();
            setUser(user);
            return user;
        } catch (err) {
            setUser(null);
            throw err;
        }
    };

    useEffect(() => {
        refreshUser()
            .finally(() => setLoading(false));
    }, []);

    return (
        <AuthContext.Provider
            value={{
                user,
                setUser,
                refreshUser,
                loading,
                authenticated: !!user
            }}
        >
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error("useAuth must be used within an AuthProvider");
    }

    return context;
}