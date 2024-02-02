package com.member.main.controller;

import com.member.main.model.Member;
import com.member.main.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.member.main.repository.MemberRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    // Create a new member
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member savedMember = memberService.saveMember(member);
        return ResponseEntity.ok(savedMember);
    }

    // Retrieve a member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve all members with pagination
    @GetMapping
    public ResponseEntity<Page<Member>> getAllMembers(Pageable pageable) {
        Page<Member> members = memberService.getAllMembers(pageable);
        return ResponseEntity.ok(members);
    }

    // Update a member
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member memberDetails) {
        Optional<Member> existingMember = memberService.getMemberById(id);
        if (existingMember.isPresent()) {
            Member updatedMember = existingMember.get();
            updatedMember.setName(memberDetails.getName());
            updatedMember.setSalary(memberDetails.getSalary());
            updatedMember.setAge(memberDetails.getAge());
            updatedMember.setJoinDate(memberDetails.getJoinDate());
            memberService.saveMember(updatedMember);
            return ResponseEntity.ok(updatedMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    // Endpoint to find members by exact name
    @GetMapping("/search/by-name")
    public List<Member> findByName(@RequestParam String name) {
        return memberRepository.findByName(name);
    }

    // Endpoint to find members by age
    @GetMapping("/search/by-age")
    public List<Member> findByAge(@RequestParam Integer age) {
        return memberRepository.findByAge(age);
    }

    // Endpoint to find members with a salary greater than or equal to a certain
    // amount
    @GetMapping("/search/by-min-salary")
    public List<Member> findBySalaryGreaterThanEqual(@RequestParam BigDecimal salary) {
        return memberRepository.findBySalaryGreaterThanEqual(salary);
    }

    // Endpoint to find members with a salary less than or equal to a certain amount
    @GetMapping("/search/by-max-salary")
    public List<Member> findBySalaryLessThanEqual(@RequestParam BigDecimal salary) {
        return memberRepository.findBySalaryLessThanEqual(salary);
    }

    // Endpoint to find members by join date
    @GetMapping("/search/by-join-date")
    public ResponseEntity<List<Member>> findByJoinDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date joinDate) {
        List<Member> members = memberRepository.findByJoinDate(joinDate);

        if (members.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if no records are found.
        }

        return ResponseEntity.ok(members);
    }

}
