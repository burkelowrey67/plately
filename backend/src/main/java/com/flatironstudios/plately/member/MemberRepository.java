package com.flatironstudios.plately.member;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByHouseholdId(UUID householdId);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.id = :id")
    Member find(@Param("householdId") UUID householdId, @Param("id") UUID id);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.birthYear < :birthYear")
    List<Member> findMemberLessThanBirthYear(@Param("householdId") UUID householdId, @Param("birthYear") int birthYear);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.birthYear > :birthYear")
    List<Member> findMemberGreaterThanBirthYear(@Param("householdId") UUID householdId, @Param("birthYear") int birthYear);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.birthYear >= :birthYearMin AND m.birthYear < :birthYearMax")
    List<Member> findMemberInBirthYearRange(@Param("householdId") UUID householdId, @Param("birthYearMin") Double birthYearMin, @Param("birthYearMax") Double birthYearMax);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.weightKgs < :weightKgs")
    List<Member> findMemberLessThanWeightKgs(@Param("householdId") UUID householdId, @Param("weightKgs") int weightKgs);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.weightKgs > :weightKgs")
    List<Member> findMemberGreaterThanWeightKgs(@Param("householdId") UUID householdId, @Param("weightKgs") int weightKgs);

    @Query("SELECT m FROM Member m WHERE m.household.id = :householdId AND m.weightKgs >= :weightKgsMin AND m.weightKgs < :weightKgsMax")
    List<Member> findMemberInWeightKgsRange(@Param("householdId") UUID householdId, @Param("weightKgsMin") Double weightKgsMin, @Param("weightKgsMax") Double weightKgsMax);

    List<Member> findByHouseholdIdAndDietType(UUID householdId, DietType dietType);

    @Query("SELECT m FROM Member m JOIN m.allergies a WHERE m.household.id = :householdId AND a = :allergen")
    List<Member> findByHouseholdIdAndAllergen(@Param("householdId") UUID householdId, @Param("allergen") Allergen allergen);

    @Query("SELECT DISTINCT a FROM Member m JOIN m.allergies a WHERE m.household.id = :householdId")
    List<Allergen> findAllAllergensByHouseholdId(@Param("householdId") UUID householdId);

    @Query("SELECT DISTINCT m.dietType FROM Member m WHERE m.household.id = :householdId")
    List<DietType> findAllDietTypesByHouseholdId(@Param("householdId") UUID householdId);
}
