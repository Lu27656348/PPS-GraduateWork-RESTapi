package com.example.demo.service;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorProjection;
import com.example.demo.interfaces.StudentGraduateWork;
import com.example.demo.interfaces.StudentGraduateWorkProjection;
import com.example.demo.interfaces.UserProjection;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent (Student student){
        return studentRepository.save(student);
    }

    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(String id){
        return studentRepository .findById(id).orElse(null);
    }

    public Student updateStudent(String id, Student student){
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if(existingStudent != null){
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudentById (String id){
        studentRepository.deleteById(id);
    }

    public ResponseEntity<List<StudentGraduateWorkProjection>> getStudentGraduateWork (String studentDNI){
        List<StudentGraduateWorkProjection> proyecciones = studentRepository.getStudentGraduateWork(studentDNI);
        return ResponseEntity.ok(proyecciones);
    }

    public ProfessorProjection getStudentCoordinator(String studentDNI){
        return studentRepository.getStudentCoordinator(studentDNI);
    }

    public Iterable<UserProjection> listStudentsData() {
        return studentRepository.listStudentsData();
    }

    public Iterable<UserProjection> listStudentsDataExceptSelected(String studentDNI) {
        return studentRepository.listStudentsDataExceptSelected(studentDNI);
    }
}
