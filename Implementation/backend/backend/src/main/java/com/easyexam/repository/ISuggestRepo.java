package com.easyexam.repository;

import com.easyexam.model.Suggest;
import com.easyexam.model.SuggestId;
import com.easyexam.model.aux.UserCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISuggestRepo extends JpaRepository<Suggest,SuggestId> {

    @Override
    List<Suggest> findAll();

    @Query(value = "SELECT * FROM problem p where p.",nativeQuery = true)
    int max();

    @Query(value = "  select id_teacher, count(*) as cantidad \n" +
            "  from suggest s\n" +
            "  group by(s.id_teacher)\t\n" +
            "  having count(*)>3;",nativeQuery = true)
    List<UserCompleted> getSuggest();
}
