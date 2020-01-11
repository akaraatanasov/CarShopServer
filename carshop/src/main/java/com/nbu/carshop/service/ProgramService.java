package com.nbu.carshop.service;

import com.nbu.carshop.entity.Program;
import com.nbu.carshop.repository.ProgramRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getPrograms() {
        List<Program> programList = new ArrayList();
        programRepository.findAll().forEach(program -> programList.add(program));

        return programList;
    }

    public Program getProgram(long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid program Id:" + id));
        return program;
    }

    public void addProgram(Program program) {
        programRepository.save(program);
    }

    public void updateProgram(long id, Program program) {
        programRepository.save(program);
    }

    public void deleteProgram(long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid program Id:" + id));
        programRepository.delete(program);
    }
}
