package com.example.todobasic.repository;

import com.example.todobasic.entity.ToDo;
import com.example.todobasic.dto.ToDodto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    @Query("SELECT new com.example.todobasic.dto.ToDodto(t) FROM ToDo t " +
            "WHERE t.user.id = :userId")
    List<ToDodto> findToDosByUserId(@Param("userId") Long userId);
}
