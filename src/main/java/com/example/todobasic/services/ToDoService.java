package com.example.todobasic.services;

import com.example.todobasic.entity.ToDo;
import com.example.todobasic.entity.User;
import com.example.todobasic.repository.ToDoRepository;
import com.example.todobasic.dto.ToDodto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

@Service
@Transactional
public class ToDoService {

    private ToDoRepository toDoRepository;

    private UserService userService;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, UserService userService) {
        this.toDoRepository = toDoRepository;
        this.userService = userService;
    }

    public Optional<ToDodto> findById(Long id) {
        return toDoRepository.findById(id).map(ToDodto::new);
    }

    public List<ToDodto> findToDosByUserId(Long userId) {
        return toDoRepository.findToDosByUserId(userId);
    }

    public void save(ToDodto toDodto) {
        Optional<String> currentUserOptional = userService.getCurrentUser();
        if (currentUserOptional.isPresent()) {
            Optional<User> userOptional = userService.getByUsername(currentUserOptional.get());
            if (userOptional.isPresent()) {
                ToDo toDo = new ToDo();
                toDo.setId(toDodto.getId());
                toDo.setDescription(toDodto.getDescription());
                toDo.setTargetDate(toDodto.getTargetDate());
                toDo.setUser(userOptional.get());
                toDoRepository.save(toDo);
            }
        }
    }

    public void delete(Long id) {
        toDoRepository.findById(id)
                .ifPresent(toDo -> toDoRepository.delete(toDo));
    }
}
