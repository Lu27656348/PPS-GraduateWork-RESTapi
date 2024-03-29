package com.example.demo.controller;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorProjection;
import com.example.demo.interfaces.StudentGraduateWork;
import com.example.demo.interfaces.StudentGraduateWorkProjection;
import com.example.demo.interfaces.UserProjection;
import com.example.demo.interfaces.projections.GetStudentGraduateWorkPending;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping
    public Iterable<Student> getAllStudents (){
        return studentService.getAllStudents();
    }

    @GetMapping("/list/data")
    public Iterable<UserProjection> listStudentsData () {
        return studentService.listStudentsData();
    }

    @GetMapping("/list/data/except/{id}")
    public Iterable<UserProjection> listStudentsDataExceptSelected (@PathVariable String id) {
        return studentService.listStudentsDataExceptSelected(id);
    }
    @GetMapping("{id}")
    public Student getStudentById (@PathVariable String id){
        System.out.println("getProfessorById");
        System.out.println(id);
        return studentService.getStudentById(id);

    }
    @PutMapping("{id}")
    public Student updateStudent (@PathVariable String id, @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }
    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable String id){
        studentService.deleteStudentById(id);
    }

    @GetMapping("/graduate-work/{id}")
    public ResponseEntity<List<StudentGraduateWorkProjection>> getStudentGraduateWork(@PathVariable String id){
        return studentService.getStudentGraduateWork(id);
    }

    @GetMapping("/coordinator/{id}")
    public ProfessorProjection getStudentCoordinator(@PathVariable String id){

        return studentService.getStudentCoordinator(id);

    }

    @GetMapping("/by/school/{id}")
    public List<UserProjection> getStudentBySchool(@PathVariable String id){
        return studentService.getStudentBySchool(id);
    }

    @GetMapping("/by/school/{id}/validate")
    public List<GetStudentGraduateWorkPending> getStudentBySchoolAndValidate(@PathVariable String id){
        return studentService.getStudentBySchoolAndValidate(id);
    }

}
