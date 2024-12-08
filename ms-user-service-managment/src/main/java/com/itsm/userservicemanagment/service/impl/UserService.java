package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.NotFoundUserExcption;
import com.itsm.userservicemanagment.dto.incoming.UserObject;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.repository.GroupRepository;
import com.itsm.userservicemanagment.repository.GroupUserRepository;
import com.itsm.userservicemanagment.repository.UserRepository;
import com.itsm.userservicemanagment.service.IUserService;
import com.itsm.userservicemanagment.tools.FieldValidator;
import com.itsm.userservicemanagment.tools.TransferUserToFromDtoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private GroupUserRepository groupUserRepository;

    @Override
    public Result create(UserObject dto) throws IllegalAccessException, NoSuchFieldException {
        User user = new User();
        Result result = new Result();

        if (checkUserObject(dto)) {
            //
        } else {

            result.setDate(LocalDateTime.now());
            result.setMessage("Error. One or more fields is null!");

            return result;
        }

        if (dto.isTech()) {
            user.setCreateDate(LocalDateTime.now());
            user.setFullName(dto.getName());
            user.setLogin(dto.getName());
            user.setTechAccount(true);
            user.setEmail(dto.getEmail());
        } else {
            user.setFamily(dto.getFamily());
            user.setName(dto.getName());
            user.setFatherName(dto.getFatherName());
            user.setEmail(dto.getEmail());
            user.setActive(true);
            user.setCreateDate(LocalDateTime.now());
            user.setJobTitle(dto.getJobTitle());
            user.setModifyDate(LocalDateTime.now());
            user.setLdapId(dto.getLdapId());
            user.setLanguage(dto.getLanguage());
            user.setTimeZone(dto.getTimeZone());
            String[] login = dto.getEmail().split("@");
            user.setLogin(login[0]);
            user.setFullName(dto.getFamily()+" "+dto.getName()+" "+dto.getFatherName());
        }

        repository.save(user);

        result.setDate(LocalDateTime.now());
        result.setMessage("Success! User create by ID:"+user.getId()+".");

        return result;

    }

    //TODO crete been validators
    private boolean checkUserObject(UserObject object) throws IllegalAccessException, NoSuchFieldException {

/*
        List<String> fieldsName = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields)
            fieldsName.add(field.getName());

        for (String field : fieldsName) {
            object.getClass().getField(field).setAccessible(true);

            if (object.getClass().getField(field).get(this) != null)
                return false;

        }

*/
        return true;

    }

    @Override
    public Result modify(UserObject user) {

        FieldValidator validator = new FieldValidator();
        validator.output();


        return null;
    }

    @Override
    public Result changePassword(Long userId, String password) {
        Result result = new Result();
        if(repository.findById(userId).isEmpty())
            throw new NotFoundUserExcption("For change password user not found!");
        else {
            User user = repository.findById(userId).get();

            user.setPassword(password);
            user.setModifyDate(LocalDateTime.now());

            repository.save(user);


            result.setDate(LocalDateTime.now());
            result.setMessage("For user by login "+user.getLogin()+" has been change password!");

            return result;
        }
    }

    @Override
    public Result delete(long id) {
        if (repository.findById(id).isEmpty()){
            Result result = new Result();
            result.setMessage("Not found user by ID :"+id);
            result.setDate(LocalDateTime.now());

            return result;
        }else{
            Result result = new Result();
            User user = repository.findById(id).get();
            user.setDeleted(true);
            user.setModifyDate(LocalDateTime.now());

            repository.save(user);

            result.setMessage("User by login "+user.getLogin()+" has marked by deleted!");
            result.setDate(LocalDateTime.now());
            return result;
        }
    }

    @Override
    public UserOutgoing getUserById(long id) {

        if(repository.findById(id).isEmpty())
            throw new NotFoundUserExcption("User not found!");

        UserOutgoing outgoing = TransferUserToFromDtoObject.getUserOutObject(repository.findById(id).get());

        return outgoing;
    }

    @Override
    public List<UserOutgoing> findUser(String login, String name) {
        return List.of();
    }

    @Override
    public List<UserOutgoing> getAllTechUser() {
        List<UserOutgoing> userList = new ArrayList<>();

        if (repository.findByTechAccountTrue().isEmpty())
            throw new NotFoundUserExcption("Not found users!");
        else {

            for (User user: repository.findByTechAccountTrue()){
                UserOutgoing outgoing = TransferUserToFromDtoObject.getUserOutObject(user);
                userList.add(outgoing);
            }

            return userList;
        }
    }

    @Override
    public List<UserOutgoing> getAllActiveNotTechUser() {
        List<UserOutgoing> userList = new ArrayList<>();

        if (repository.findByActiveTrue() == null)
            throw new NotFoundUserExcption("Not found users!");
        else {

            for (User user: repository.findByActiveTrue()){
                UserOutgoing outgoing = TransferUserToFromDtoObject.getUserOutObject(user);
                userList.add(outgoing);
            }

            return userList;
        }
    }

    @Override
    public List<UserOutgoing> getAllUser() {
        List<UserOutgoing> userList = new ArrayList<>();

        if (repository.findAll() == null)
            throw new NotFoundUserExcption("Not found users!");
        else {

            for (User user: repository.findAll()){
                UserOutgoing outgoing = TransferUserToFromDtoObject.getUserOutObject(user);
                userList.add(outgoing);
            }

            return userList;
        }

    }

    @Override
    public List<UserOutgoing> getDeleteUser() {
        List<UserOutgoing> userList = new ArrayList<>();

        if (repository.findByDeletedTrue() == null)
            throw  new NotFoundUserExcption("Not found users!");
        else{

            for (User user: repository.findByDeletedTrue()){
                UserOutgoing outgoing = TransferUserToFromDtoObject.getUserOutObject(user);
                userList.add(outgoing);
            }

        }


        return userList;
    }

    @Override
    public List<UserOutgoing> getNotActiveUser() {
        List<UserOutgoing> userList = new ArrayList<>();

        if (repository.findByActiveFalse() == null)
            throw  new NotFoundUserExcption("Not found users!");
        else{

            for (User user: repository.findByActiveFalse()){
                UserOutgoing outgoing = TransferUserToFromDtoObject.getUserOutObject(user);
                userList.add(outgoing);
            }

        }


        return userList;
    }
}
