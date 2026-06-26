import "./Form.css"

export default function Form({
    children,
    className = "",
    onSubmit
}) {
    return (
        <form
            className={`form ${className}`}
            onSubmit={onSubmit}
        >
            {children}
        </form>
    );
}