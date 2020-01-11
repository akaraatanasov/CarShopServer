package com.nbu.carshop.controller;

import com.nbu.carshop.entity.Program;
import com.nbu.carshop.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @RequestMapping("/programs")
    public String  getPrograms(Model model) {
        model.addAttribute("programs", programService.getPrograms());
        return "programs";
    }

    @RequestMapping("/programs/{id}")
    Program getProgram(@PathVariable("id") int id) {
        return programService.getProgram(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/programs")
    void addProgram(@RequestBody Program program) {
        programService.addProgram(program);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/programs/{id}")
    void updateProgram(@PathVariable("id") int id, @RequestBody Program program) {
        programService.updateProgram(id, program);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/programs/{id}")
    void deleteProgram(@PathVariable("id") int id) {
        programService.deleteProgram(id);
    }

}
