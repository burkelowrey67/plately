import { useEffect, useState } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { getCurrentUser } from "../auth/authApi";
import { useAuth } from "../auth/AuthContext";

export default function ProtectedRoute() {
    const  [authenticated, setAuthenticated] = useState(false);
    const { user, loading } = useAuth();

    if (!user) return <Navigate to="/login" replace />;
    if (user.onboardStep !== "COMPLETED") {
        return <Navigate to="/onboard/welcome" replace />;
    }

    return <Outlet />;
}