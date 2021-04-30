package com.auto.message.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.auto.message.dto.Member;

@Mapper
public interface MemberMapper {
	
	public List<Member> selectMemberList() throws Exception;
	
}
