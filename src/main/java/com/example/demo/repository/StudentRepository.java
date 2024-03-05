package com.example.demo.repository;

import com.example.demo.entity.GraduateWork;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.interfaces.ProfessorProjection;
import com.example.demo.interfaces.StudentGraduateWork;
import com.example.demo.interfaces.StudentGraduateWorkProjection;
import com.example.demo.interfaces.UserProjection;
import com.example.demo.interfaces.projections.GetStudentGraduateWorkPending;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student,String> {
    @Query(
            value = "SELECT Gw.graduateworkid,Gw.graduateworktitle,Gw.graduateworkstatuscode\n" +
                    "FROM GraduateWork Gw, student_graduatework Sgw\n" +
                    "WHERE Gw.graduateworkid = sgw.graduateworkid\n" +
                    "AND sgw.studentdni = :studentDNI",
            nativeQuery = true
    )
    List<StudentGraduateWorkProjection> getStudentGraduateWork(@Param("studentDNI") String studentDNI);

    @Query(
            value = "SELECT Pr.*\n" +
                    "FROM Professors Pr, Students Stu\n" +
                    "WHERE Pr.professorRole = 'Coordinator'\n" +
                    "AND Pr.professorSchoolName = Stu.studentSchoolName\n" +
                    "AND Stu.studentdni = :studentDNI",
            nativeQuery = true
    )
    ProfessorProjection getStudentCoordinator(@Param("studentDNI") String studentDNI);

    @Query(
            value = "SELECT U.*\n" +
                    "FROM Users as U, Students as S\n" +
                    "WHERE U.userDNI = S.studentDNI",
            nativeQuery = true
    )
    Iterable<UserProjection> listStudentsData();

    @Query(
            value = "SELECT U.*\n" +
                    "FROM Users as U, Students as S\n" +
                    "WHERE U.userDNI = S.studentDNI\n" +
                    "AND S.studentDNI != :studentDNI",
            nativeQuery = true
    )
    Iterable<UserProjection> listStudentsDataExceptSelected(@Param("studentDNI") String studentDNI);

    @Query(
            value = "SELECT U.*\n" +
                    "FROM Users as U, Students as S\n" +
                    "WHERE U.userDNI = S.studentDNI\n" +
                    "AND U.schoolName = :schoolName",
            nativeQuery = true
    )

    public List<UserProjection> getStudentBySchool(@Param("schoolName") String id );


    @Query(
            value = "SELECT TableSt.*\n" +
                    "FROM (\n" +
                    "\tSELECT Est.studentDNI\n" +
                    "\tFROM students AS Est\n" +
                    "\tLEFT JOIN student_graduatework ON Est.studentDNI = student_graduatework.studentDNI\n" +
                    "\tWHERE student_graduatework.studentDNI IS NULL\n" +
                    "\n" +
                    "\tUNION\n" +
                    "\t\n" +
                    "\tSELECT Est.studentDNI\n" +
                    "\tFROM students AS Est,Users\n" +
                    "\tWHERE (SELECT COUNT(Gw.*) FROM GraduateWork AS Gw, student_graduatework AS Sgw WHERE Sgw.studentDNI = Est.studentDNI AND Gw.graduateWorkId = Sgw.graduateWorkId AND Gw.graduateworkstatuscode NOT IN (400,100,401)) = 0\n" +
                    "\tAND (SELECT COUNT(Gw.*) FROM GraduateWork AS Gw, student_graduatework AS Sgw WHERE Sgw.studentDNI = Est.studentDNI AND Gw.graduateWorkId = Sgw.graduateWorkId AND Gw.graduateworkstatuscode IN (100)) < 1\n" +
                    "   \n" +
                    ") AS TableSt, Users\n" +
                    "WHERE Users.userDNI = TableSt.studentDNI\n" +
                    "AND Users.schoolName = :schoolName\n" +
                    "GROUP BY studentDNI",
            nativeQuery = true
    )

    public List<GetStudentGraduateWorkPending> getStudentBySchoolAndValidate(@Param("schoolName") String id );
}
