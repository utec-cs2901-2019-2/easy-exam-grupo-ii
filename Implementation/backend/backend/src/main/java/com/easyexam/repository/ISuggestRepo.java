package com.easyexam.repository;

import com.easyexam.model.Suggest;
import com.easyexam.model.SuggestId;
import com.easyexam.model.aux.UserCompleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ISuggestRepo extends JpaRepository<Suggest,SuggestId> {

    @Override
    List<Suggest> findAll();

    @Modifying
    @Query(value="insert into suggest values (:idProblem, :idTeacher, :comment, true)", nativeQuery=true)
    int reportProblem(@Param("comment") String comment, @Param("idProblem")int idProblem, @Param("idTeacher")int idTeacher);

    @Query(value = "SELECT * FROM problem p where p.",nativeQuery = true)
    int max();

    @Query(value =  "  select count(*) \n" +
                    "  from suggest s\n" +
                    "  where s.id_teacher = :id ;",nativeQuery = true)
    int getCountByTeacherId(@Param("id")int id);

    @Query(value = "  select id_teacher \n" +
            "  from suggest s\n" +
            "  where is_reported = true \n" +
            "  group by(s.id_teacher)\t\n" +
            "  having count(*)>3;",nativeQuery = true)
    List<Integer> getSuggest();

    @Query(value = "  select id_problem \n" +
            "  from suggest s\n" +
            "  where is_reported = true ;",nativeQuery = true)
    List<Integer> getReportedProblems();

    Suggest findBySuggestId(SuggestId suggestId);
}
