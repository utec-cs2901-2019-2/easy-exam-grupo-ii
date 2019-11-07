package com.easyexam.repository;

import com.easyexam.model.Suggest;
import com.easyexam.model.SuggestId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISuggestRepo extends JpaRepository<Suggest,SuggestId> {

    @Override
    List<Suggest> findAll();

}
