import { useEffect, useState } from "react";
import { MemberListCard } from "./MemberListCard";
import { MemberEditorCard } from "./MemberEditorCard";
import { getMembers } from "../member/memberApi";
import { useAuth } from "../auth/AuthContext";

export default function AddMembersCard({onDone}) {
    const [members, setMembers] = useState([]);
    const [editingMember, setEditingMember] = useState(null);
    const [isAdding, setIsAdding] = useState(false);
    const { refreshUser } = useAuth();

    async function loadMembers() {
        try {
            console.log("Loading members...");
            const members = await getMembers();
            console.log("Received:", members);
            setMembers(members);
        } catch (e) {
            console.error(e);
        }
    }

    useEffect(() => {
        loadMembers();
    }, []);

    function handleEdit(member) {
        setEditingMember(member);
        setIsAdding(false);
    }

    function handleAdd() {
        setEditingMember(null);
        setIsAdding(true);
    }

    function handleClose() {
        setEditingMember(null);
        setIsAdding(false);
    }

    async function handleMemberSaved() {
        console.log("Saving!")
        await loadMembers();
    }

    if (editingMember || isAdding) {
        return (
            <MemberEditorCard
                member={editingMember}
                onSave={handleMemberSaved}
                onClose={handleClose}
            />
        );
    }

    return (
        <MemberListCard
            members={members}
            onEdit={handleEdit}
            onAdd={handleAdd}
            onDone={onDone}
        />
    );
}