export default function MealList({ meals }) {
  if (!meals || meals.length === 0) {
    return <p>No meals found.</p>;
  }

  return (
    <div>
      <h2>Meals</h2>
      <ul>
        {meals.map((meal, i) => (
          <li key={i}>{meal}</li>
        ))}
      </ul>
    </div>
  );
}