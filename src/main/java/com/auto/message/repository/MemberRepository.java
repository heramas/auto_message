package com.auto.message.repository;

import com.auto.message.dto.Member;

public interface MemberRepository {

	Member findByNameNoCache(String name);
    Member findByNameCache(String name);
    void refresh(String name);
}
