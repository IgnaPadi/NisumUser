package com.nisum.service;

import com.nisum.entity.User;
import com.nisum.repository.UserRepository;
import com.nisum.response.ResponseApi;
import com.nisum.response.ResponseApiMessage;
import com.nisum.response.ResponseApiMessageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTests {


	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testRegisterOk(){
		Optional<User> optionalUserOld = userRepository.findByEmail("juan@rodriguez.cl");
		optionalUserOld.ifPresent(user -> userRepository.delete(user));

		User user = new User("name", "juan@rodriguez.cl", "a2asfGfdfdf4", new ArrayList<>());
		ResponseEntity<Object> responseEntity = userService.register(user);

		System.out.println(responseEntity.getBody().toString());
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity);
		User userResponseApi = (User) responseEntity.getBody();

		Assertions.assertNotNull(userResponseApi);
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		Assertions.assertEquals(user.getEmail(), userResponseApi.getEmail());
		Assertions.assertEquals(user.getPassword(), userResponseApi.getPassword());
	}

	@Test
	public void testSetRegisterNoOk(){
		User user = new User("name", null, null, new ArrayList<>());

		ResponseEntity<Object> responseEntity= userService.register(user);
		ResponseApi responseApi = (ResponseApi) responseEntity.getBody();

		Assertions.assertNotNull(responseApi);
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

		Optional <ResponseApiMessage> optionalResponseApiMessageEmail = responseApi.getResponseApiMessageList().stream().filter(
				i-> i.getCodigo() == ResponseApiMessageEnum.ERROR_NOT_NULL.getCode()).findFirst() ;
		Assertions.assertTrue(optionalResponseApiMessageEmail.isPresent());

		ResponseEntity<Object> responseEntityException = userService.register(null);
		Assertions.assertEquals(responseEntityException.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testRegisterNoOkUserExists(){
		Optional<User> optionalUserOld = userRepository.findByEmail("juan@rodriguez.cl");
		optionalUserOld.ifPresent(user -> userRepository.delete(user));

		User user = new User("name", "juan@rodriguez.cl", "a2asfGfdfdf4", new ArrayList<>());
		ResponseEntity<Object> responseEntity = userService.register(user);
		User userResponseApi = (User) responseEntity.getBody();

		Assertions.assertNotNull(userResponseApi);
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

		ResponseEntity<Object> responseEntityExists = userService.register(user);
		ResponseApi responseApi = (ResponseApi) responseEntityExists.getBody();

		Assertions.assertNotNull(responseApi);
		Assertions.assertEquals(responseEntityExists.getStatusCode(), HttpStatus.BAD_REQUEST);

		Optional <ResponseApiMessage> optionalResponseApiMessageUserExists = responseApi.getResponseApiMessageList().stream().filter(
				i-> i.getCodigo() == ResponseApiMessageEnum.ERROR_USER_EXISTS.getCode()).findFirst() ;

		Assertions.assertTrue(optionalResponseApiMessageUserExists.isPresent());

	}


	@Test
	public void testRegisterNoOkBusinessValidation(){
		//Email sin @ y password con solamente un número
		User user = new User("name", "juliotestsswd.cl", "a2asfGffdf", new ArrayList<>());

		ResponseEntity<Object> responseEntity= userService.register(user);
		ResponseApi responseApi = (ResponseApi) responseEntity.getBody();

		Assertions.assertNotNull(responseApi);
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

		Optional <ResponseApiMessage> optionalResponseApiMessageEmail = responseApi.getResponseApiMessageList().stream().filter(
				i-> i.getCodigo() == ResponseApiMessageEnum.ERROR_EMAIL.getCode()).findFirst() ;
		Assertions.assertTrue(optionalResponseApiMessageEmail.isPresent());

		Optional <ResponseApiMessage> optionalResponseApiMessagePassword = responseApi.getResponseApiMessageList().stream().filter(
				i-> i.getCodigo() == ResponseApiMessageEnum.ERROR_PASSWORD.getCode()).findFirst() ;
		Assertions.assertTrue(optionalResponseApiMessagePassword.isPresent());

	}

}
