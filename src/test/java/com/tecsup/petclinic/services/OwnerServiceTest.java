package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

}