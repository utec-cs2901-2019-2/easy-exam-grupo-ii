package com.easyexam.repository;

import com.easyexam.model.Teacher;
import com.easyexam.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ITeacherRepo extends JpaRepository<Teacher,Integer> {
    @Override
    List<Teacher> findAll();
    Teacher findTeacherById(int id);
    Teacher findByUser(User user);

    @Query(value = "SELECT t.bonus FROM teacher t where t.user_id=?1",nativeQuery = true)
    int getBonus(int idTeacher);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE TEACHER  SET bonus = :bonus WHERE user_id = :iduser",nativeQuery = true)
    void updateBonus(@Param("bonus") int bonus,@Param("iduser") int idTeacher);
}
