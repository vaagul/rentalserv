package com.atlantis.rentalserv.db;

import com.atlantis.rentalserv.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository  extends JpaRepository<Branch, Long> {

    List<Branch> findByBranchName(String branchName);
}
