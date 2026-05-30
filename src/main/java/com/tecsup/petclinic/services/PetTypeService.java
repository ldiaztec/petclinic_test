package com.tecsup.petclinic.services;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PetTypeService {
    public Map<String, Object> create(Map<String, Object> type) { type.put("id", 1); return type; }
    public Map<String, Object> findById(Integer id) { Map<String, Object> t = new HashMap<>(); t.put("id", id); t.put("name", "Dog"); return t; }
    public Map<String, Object> update(Map<String, Object> type) { return type; }
    public void delete(Integer id) {}
}