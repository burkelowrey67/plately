import "../style/layouts.css"
import "../style/utilities.css"
import { Card } from "../components/card";


export default function MemberSummaryCard({
    onClick,
    member,
    children
}) {
    return (
        <Card onClick={onClick} className="flex row space-between center">
            <div className="flex column">
                <h3>{member.name}</h3>
                <p>{member.dietType}</p>
            </div>

            <div className="flex column">
                <span>{member.weightKgs} kgs</span>
                <span>{member.heightMeters} m</span>
            </div>
        </Card>
    );
}