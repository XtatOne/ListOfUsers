package com.xtatone.listOfUsers.dao;

import com.xtatone.listOfUsers.entity.History;
import com.xtatone.listOfUsers.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
