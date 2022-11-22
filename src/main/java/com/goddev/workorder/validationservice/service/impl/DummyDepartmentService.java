package com.goddev.workorder.validationservice.service.impl;

import com.goddev.workorder.validationservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class DummyDepartmentService implements DepartmentService {
    @Override
    public Set<String> getDepartments() {
        return Set.of("GOoD analysis department","GOoD repair department", "GOoD replacement department");
    }
}
