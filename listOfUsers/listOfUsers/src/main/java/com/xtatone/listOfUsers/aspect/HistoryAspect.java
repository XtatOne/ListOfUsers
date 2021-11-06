package com.xtatone.listOfUsers.aspect;

import com.xtatone.listOfUsers.entity.History;
import com.xtatone.listOfUsers.entity.Users;
import com.xtatone.listOfUsers.service.HistoryService.HistoryServiceImpl;
import com.xtatone.listOfUsers.service.UsersServeice.UsersService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@Aspect
public class HistoryAspect {

    private Users oldUser;

    @Autowired
    private HistoryServiceImpl historyService;

    @Autowired
    private UsersService usersService;

    @Pointcut("execution(public String addUser(..))")
    public void addUserPointcut() {}

    @Pointcut("execution(public String editUser(..))")
    public void editUserPointcut() {}

    @Before("execution(public String showEditForm(..))")
    public void beforeShowEditForm(JoinPoint joinPoint) {

        Object[] listOfArg = joinPoint.getArgs();

        Optional idOptional = Arrays.stream(listOfArg).findFirst();

        if (idOptional.isPresent()) {

            int userID = (int) idOptional.get();

            Optional<Users> oldUserOptional = usersService.getUser(userID);

            if (oldUserOptional.isPresent()) {
                oldUser = oldUserOptional.get();
            }

        }

    }

    @After("addUserPointcut()")
    public void afterAddUser(JoinPoint joinPoint) {
        addHistory(joinPoint, "Добавлен новый пользователь ", false, false);
    }

    @After("editUserPointcut()")
    public void afterEditUser(JoinPoint joinPoint) {
        addHistory(joinPoint, "Внесены изменения в профиль ", false, true);
    }

    @After("execution(public String deleteUser(..))")
    public void afterDeleteUser(JoinPoint joinPoint) {
        addHistory(joinPoint, "Удален пользователь с id ", true, false);
    }

    private void addHistory(JoinPoint joinPoint, String message, boolean deletion, boolean modification) {

        Object[] listOfArg = joinPoint.getArgs();


        Optional<Object> optionalArg = Arrays.stream(listOfArg).findFirst();

        if (optionalArg.isPresent()) {

            if (deletion) {
                int userId = (int) optionalArg.get();
                historyService.saveHistory(new History(message + " " + userId));
            } else {

                Users user = (Users) optionalArg.get();

                if (modification) {
                    String changes = makeChanges(user);
                    historyService.saveHistory(new History(message + " " + user.getName() + ". " + changes));
                } else {
                    historyService.saveHistory(new History(message + " " + user.getName()));
                }

            }
        }

    }

    private String makeChanges(Users newUser) {

        String messageOfChanges = "";

        String oldName = oldUser.getName();
        String newName = newUser.getName();

        String oldSurName = oldUser.getSurname();
        String newSurName = newUser.getSurname();

        String oldDepartment = oldUser.getDepartment();
        String newDepartment = newUser.getDepartment();


        if (!oldName.equals(newName)) {
            messageOfChanges = messageOfChanges + " Изменено имя. Было - " +
                    oldName + ", cтало - " + newName + ".";
        }

        if (!oldSurName.equals(newSurName)) {
            messageOfChanges = messageOfChanges + " Изменена фамилия. Было - " +
                    oldSurName + ", cтало - " + newSurName  + ".";
        }

        if (!oldDepartment.equals(newDepartment)) {
            messageOfChanges = messageOfChanges + " Изменено место работы. Было - " +
                    oldDepartment + ", cтало - " + newDepartment  + ".";
        }

        if (messageOfChanges.isEmpty()) {
            messageOfChanges = "Данные не отличаются от предыдущей версии.";
        }

        return  messageOfChanges;
    }

}
