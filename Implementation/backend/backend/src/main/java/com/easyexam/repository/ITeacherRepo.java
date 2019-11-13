package com.easyexam.repository;

import com.easyexam.model.Teacher;
import com.easyexam.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeacherRepo extends JpaRepository<Teacher,Integer> {
    @Override
    List<Teacher> findAll();
    Teacher findTeacherById(int id);
    Teacher findByUser(User user);

    @Query(value = "SELECT t.bonus FROM teacher t where t.user_id=?1",nativeQuery = true)
    int getBonus(int idTeacher);
//    @Modifying
//    @Query("UPDATE TEACHER T SET T. = :address WHERE c.id = :companyId")
//    int updateAddress(@Param("companyId") int companyId, @Param("address") String address);
}
