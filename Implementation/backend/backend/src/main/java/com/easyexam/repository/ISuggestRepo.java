package com.easyexam.repository;

import com.easyexam.model.Suggest;
import com.easyexam.model.SuggestId;
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
}
