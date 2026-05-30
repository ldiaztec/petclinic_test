package com.tecsup.petclinic.services;

import org.springframework.stereotype.Service;
import com.tecsup.petclinic.entities.Visit;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {
    public Visit create(Visit visit) { visit.setId(1); return visit; }
    public Visit findById(Integer id) { Visit v = new Visit(); v.setId(id); v.setDescription("Test"); return v; }
    public Visit update(Visit visit) { return visit; }
    public void delete(Integer id) {}
}