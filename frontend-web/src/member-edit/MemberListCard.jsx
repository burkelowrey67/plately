import "../style/utilities.css"
import "../style/layouts.css"
import "./MemberCards.css"

import { Button } from "../components/button";
import { Card } from "../components/card";
import MemberSummaryCard from "../member/MemberSummaryCard";

export function MemberListCard({ members, onEdit, onAdd, onDone }) {

    return (
        <Card className="max-width-md full-width member-card">
            <div className="vertical-group gap-md">
                <h2>Who are we shopping for?</h2>

                <Card className="gap-md member-list">

                    <Card onClick={onAdd}>
                        <div className="centered-group">
                            <h2>add member</h2>
                        </div>
                    </Card>

                    {members.map(member => (
                        <MemberSummaryCard
                            key={member.id}
                            member={member}
                            onClick={() => onEdit(member)}
                        />
                    ))}
                </Card>

                <Button className="full-width" onClick={onDone}>
                    done
                </Button>
            </div>
        </Card>
    );
}