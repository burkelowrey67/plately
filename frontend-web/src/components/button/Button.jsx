import "./Button.css"

export default function Button({
    children,
    variant,
    type,
    onClick,
    className=""
}) {
    return (
        <button
            className={`button button-${variant} ${className}`}
            type={type}
            onClick={onClick}
        >
            {children}
        </button>
    );
}