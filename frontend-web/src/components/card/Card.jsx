import './Card.css';

export default function Card({ children, className = "", onClick }) {
    return (
        <div 
            className={`card ${onClick ? "card-clickable" : ""} ${className}`}
            onClick={onClick}
        >
            {children}
        </div>
    );
}