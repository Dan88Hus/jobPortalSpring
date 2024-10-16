package com.huseyin.jobportal.repository;

import com.huseyin.jobportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users,Integer> {

}
