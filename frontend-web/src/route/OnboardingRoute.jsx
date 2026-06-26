import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";

export default function OnboardingRoute() {
    const { user, loading } = useAuth();
    const location = useLocation();

    if (loading) {
        return <p>Loading...</p>;
    }

    if (!user) {
        return <Navigate to="/login" replace />;
    }

    const stepToPath = {
        WELCOME: "/onboard/welcome",
        NAME: "/onboard/name",
        HOUSEHOLD: "/onboard/household",
        MEMBERS: "/onboard/household/members",
        COMPLETED: "/dashboard"
    };

    const expectedPath = stepToPath[user.onboardStep];

    if (location.pathname !== expectedPath) {
        return <Navigate to={expectedPath} replace />;
    }

    return <Outlet />;
}