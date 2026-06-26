import "./MemberCards.css"
import "../style/layouts.css"
import "../style/utilities.css"
import "./MemberCards.css"

import { useState } from "react";
import { MemberSummaryCard } from "../member";
import { Card } from "../components/card";
import { Form } from "../components/form";
import { Button } from "../components/button";
import { createMember, editMember } from "../member/memberApi";

export function MemberEditorCard({ member, onSave, onClose }) {
    const [name, setName] = useState(member?.name ?? "");
    const [age, setAge] = useState(member?.ageYrs ?? "");
    const [height, setHeight] = useState(member?.heightMeters ?? "");
    const [weight, setWeight] = useState(member?.weightKgs ?? "");
    const [weightGoal, setWeightGoal] = useState(member?.weightGoalKgs ?? "");
    const [dietType, setDietType] = useState(member?.dietType ?? "NONE");
    const [allergies, setAllergies] = useState(member?.allergies ??  []);


    async function handleSave() {
        try {
            if (member) {
                await editMember(member.id, {
                    name,
                    dietType,
                    allergies,
                    ageYrs: Number(age),
                    heightMeters: Number(height),
                    weightKgs: Number(weight),
                    weightGoalKgs: Number(weightGoal)
                });
            } else {
                await createMember({
                    name,
                    dietType,
                    allergies,
                    ageYrs: Number(age),
                    heightMeters: Number(height),
                    weightKgs: Number(weight),
                    weightGoalKgs: Number(weightGoal)
                });
            }

            await onSave();
            onClose();
        } catch (e) {
            console.error(e);
        }
    }

    return (
        <Card className="max-width-lg full-width">
            <Form>

                <h2>
                    {member ? "Edit Member" : "Add Member"}
                </h2>

                <div className="form-group">
                    <label>Name</label>
                    <input
                        value={name}
                        placeholder="John Doe"
                        onChange={e => setName(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label>Age</label>
                    <input
                        value={age}
                        placeholder="years"
                        onChange={e => setAge(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label>Height</label>
                    <input
                        value={height}
                        placeholder="meters"
                        onChange={e => setHeight(e.target.value)}
                    />
                </div>


                <div className="form-group">
                    <label>Weight</label>
                    <input
                        value={weight}
                        placeholder="kgs"
                        onChange={e => setWeight(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label>Weight Goal</label>
                    <input
                        value={weightGoal}
                        placeholder="kgs"
                        onChange={e => setWeightGoal(e.target.value)}
                    />
                </div>

                <div className="horizontal-group centered-group gap-sm">
                    <Button className="full-width" variant="danger" onClick={onClose}>
                        cancel
                    </Button>

                    <Button className="full-width" onClick={handleSave}>
                        save
                    </Button>
                </div>

            </Form>
        </Card>
    );
}