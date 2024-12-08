package com.itsm.userservicemanagment.tools;

import com.itsm.userservicemanagment.dto.incoming.UserObject;
import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class FieldValidator {

    private Map<String, Object> userMap = new HashMap<>();

    private Map<String, Object> getMapFromUser(User user){


        Field[] fields = user.getClass().getFields();

        System.out.println("Field name:");
        for (Field f: fields)
            System.out.println(f.getName());


        System.out.println("Field type:");
        for (Field f: fields)
            System.out.println(f.getClass());


        for (Field field: fields)
            userMap.put(field.getName(), field.getClass());

        return userMap;

    }

    private Map<String, Object> getMapFromUserObject(UserObject income){

        return null;

    }

    public void output(){

        User uuu = new User();

        getMapFromUser(uuu);

        System.out.println("JDFKJDKJFKDJFKDJKFJDKFJKDJFKDLF");

        for (String key : userMap.keySet()) {
            System.out.println(key + "=" + userMap.get(key));
        }



    }

}
