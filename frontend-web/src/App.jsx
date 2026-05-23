import { useState, useEffect } from "react";
import axios from "axios";
import MealList from "./components/MealList";

function App() {
  const [meals, setMeals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get(`${import.meta.env.VITE_API_BASE_URL}/meals`)
      .then((response) => {
        setMeals(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError("Failed to load meals.");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error)   return <p>{error}</p>;

  return (
    <div>
      <h1>Plately</h1>
      <MealList meals={meals} />
    </div>
  );
}

export default App;
