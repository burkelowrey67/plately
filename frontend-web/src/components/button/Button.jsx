import "./Button"

export default function Button({
    children,
    type,
    onClick
}) {
    return (
        <button
            className="btn"
            type={type}
            onClick={onClick}
        >
            {children}
        </button>
    );
}