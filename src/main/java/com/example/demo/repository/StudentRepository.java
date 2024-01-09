package com.example.demo.repository;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorProjection;
import com.example.demo.interfaces.StudentGraduateWork;
import com.example.demo.interfaces.StudentGraduateWorkProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student,String> {
    @Query(
            value = "SELECT Gw.graduateworkid,Gw.graduateworktitle,Gw.graduateworkestatuscode\n" +
                    "FROM GraduateWork Gw, student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkid = sgw.graduateworkid\n" +
                    "AND sgw.studentdni = :studentDNI",
            nativeQuery = true
    )
    List<StudentGraduateWorkProjection> getStudentGraduateWork(@Param("studentDNI") String studentDNI);

    @Query(
            value = "SELECT Pr.*\n" +
                    "FROM Professors Pr, Students Stu\n" +
                    "WHERE Pr.professorProfession = 'Coordinator'\n" +
                    "AND Pr.professorSchoolName = Stu.studentSchoolName\n" +
                    "AND Stu.studentdni = :studentDNI",
            nativeQuery = true
    )
    ProfessorProjection getStudentCoordinator(@Param("studentDNI") String studentDNI);

}
