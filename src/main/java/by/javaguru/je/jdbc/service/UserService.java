package by.javaguru.je.jdbc.service;

import by.javaguru.je.jdbc.dao.UserDao;
import by.javaguru.je.jdbc.dto.CreateUserDto;
import by.javaguru.je.jdbc.dto.UserDto;
import by.javaguru.je.jdbc.exceptions.ValidationException;
import by.javaguru.je.jdbc.mapper.CreateUserMapper;
import by.javaguru.je.jdbc.mapper.UserMapper;
import by.javaguru.je.jdbc.validator.CreateUserValidator;
import by.javaguru.je.jdbc.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Integer createUser(CreateUserDto createUserDto){

        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var user = createUserMapper.mapFrom(createUserDto);
        var result = userDao.save(user);
        return result.getId();
    }


    public static UserService getInstance() {
        return INSTANCE;
    }

    public Optional<UserDto> login(String email, String pwd) {
        return userDao.findByEmailAndPassword(email,pwd).map(userMapper::mapFrom);
    }
}


