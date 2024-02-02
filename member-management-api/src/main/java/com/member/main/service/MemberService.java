package com.member.main.service;

import com.member.main.model.Member;
import com.member.main.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Create or Update a member
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // Retrieve a member by ID
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // Retrieve all members with pagination
    public Page<Member> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    // Delete a member by ID
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // Custom business logic to update member salary
    public Member updateMemberSalary(Long id, BigDecimal newSalary) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setSalary(newSalary);
            return memberRepository.save(member);
        } else {
            // Handle the case where the member is not found
            throw new RuntimeException("Member not found with id " + id);
        }
    }
}
