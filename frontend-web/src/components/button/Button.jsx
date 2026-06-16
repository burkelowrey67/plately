import "./Button.css"

export default function Button({
    children,
    type,
    onClick,
    className=""
}) {
    return (
        <button
            className={`button ${className}`}
            type={type}
            onClick={onClick}
        >
            {children}
        </button>
    );
}