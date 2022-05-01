package com.atlantis.rentalserv.model.entity;

import com.atlantis.rentalserv.db.BranchRepository;
import com.atlantis.rentalserv.enums.BranchStatus;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

@SpringBootTest
public class BranchTest {
    @Autowired
    BranchRepository branchRepository;

    @Test
    public void branchDataIntegrityTest() {
        Branch branch = Branch.builder().id(null).address("here").status(BranchStatus.OPEN).build();
//        branchRepository.save(branch);
        assertThrows(DataIntegrityViolationException.class, () -> branchRepository.save(branch));
//        Branch dbBranch = branchRepository.findByBranchName("name").get(0);
//        assertEquals(branch.getBranchName(), dbBranch.getBranchName());
    }

    @Test
    public void branchSaveTest() {
        Branch branch = Branch.builder().id(null).address("here").branchName("b1").status(BranchStatus.OPEN).build();
        branchRepository.save(branch);
        Branch dbBranch = branchRepository.findByBranchName("b1").get(0);
        assertEquals(branch.getBranchName(), dbBranch.getBranchName());
    }
}
