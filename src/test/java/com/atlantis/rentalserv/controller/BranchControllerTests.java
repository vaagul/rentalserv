package com.atlantis.rentalserv.controller;

import com.atlantis.rentalserv.AbstractTest;
import com.atlantis.rentalserv.controller.BranchController;
import com.atlantis.rentalserv.model.entity.Branch;
import com.atlantis.rentalserv.model.request.AddBranchRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import({BranchController.class})
public class BranchControllerTests extends AbstractTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private BranchController controller;

    @Test
    public void createBranchTests() throws Exception {
        String uri = "/branch";
        AddBranchRequest addBranchRequest = new AddBranchRequest("b1", "BIKE,CAR");
        String inputJson = super.mapToJson(addBranchRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("b1"));
    }

    @Test
    public void getBranchTest() throws Exception{
        String uri = "/branch/b1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
//        List<Branch> branchList = new ObjectMapper().readValue(content, new TypeReference<List<Branch>>(){});
//        assertNotEquals(0, branchList.size());
        assertTrue(content.contains("b1"));
    }

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

}
