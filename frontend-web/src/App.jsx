import { Routes, Route } from "react-router-dom";

import LoginPage from "./auth/LoginPage";
import RegisterPage from "./auth/RegisterPage";
import ProtectedRoute from "./route/ProtectedRoute";
import OnboardNamePage from "./onboard/NamePage";
import OnboardWelcomePage from "./onboard/WelcomePage";
import OnboardingRoute from "./route/OnboardingRoute";
import HouseholdPage from "./onboard/HouseholdPage";
import MembersPage from "./onboard/MembersPage";

function App() {
    return (
        <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />

            <Route element={<OnboardingRoute />}>
                <Route path="/onboard/welcome" element={<OnboardWelcomePage />} />
                <Route path="/onboard/name" element={<OnboardNamePage />} />
                <Route path="/onboard/household" element={<HouseholdPage />} />
                <Route path="/onboard/household/members" element={<MembersPage />} />
            </Route>

          <Route element={<ProtectedRoute />}>
              <Route path="/" element={<LoginPage />} />
              <Route path="/dashboard" element={<h1>Dashboard</h1>}/>
          </Route>
        </Routes>
    );
}

export default App;
