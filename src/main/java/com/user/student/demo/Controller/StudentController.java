package com.user.student.demo.Controller;

import com.user.student.demo.Model.Student;
import com.user.student.demo.Service.StudentService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService)
    {
        super();
        this.studentService = studentService;
    }

    //handler method
    @GetMapping("/students")
    public String listOfStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createNewStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentDetail(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model){
      Student oldStudentData = studentService.getStudentById(id);
        oldStudentData.setId(id);
        oldStudentData.setFirstName(student.getFirstName());
        oldStudentData.setLastName(student.getLastName());
        oldStudentData.setEmail(student.getEmail());

        studentService.updateStudent(oldStudentData);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudentData(@PathVariable Long id){
        studentService.deleteStudentDataById(id);
        return "redirect:/students";
    }
    }
