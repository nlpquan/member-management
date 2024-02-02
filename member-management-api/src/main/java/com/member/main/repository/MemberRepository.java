package com.member.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.member.main.model.Member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find members by exact name
    @Query("SELECT m FROM Member m WHERE m.name LIKE %:name%")
    List<Member> findByName(@Param("name") String name);

    // Find members by age
    List<Member> findByAge(Integer age);

    // Find members with a salary greater than or equal to the specified amount
    List<Member> findBySalaryGreaterThanEqual(BigDecimal salary);

    // Find members with a salary less than or equal to the specified amount
    List<Member> findBySalaryLessThanEqual(BigDecimal salary);

    // Find members by join date
    List<Member> findByJoinDate(Date joinDate);
}
