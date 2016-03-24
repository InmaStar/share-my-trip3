package uo.sdi.util.random;

import uo.sdi.transport.UserDTO;

public class RandomUserGenerator {
    private static int users = 0;
    private static final Long USER_ID_INIT_VALUE = 500L;
    private static String[] apellidos = {"Alvarez", "Lopez", "Perez", "Fernandez", "Martinez", "Gomez"};
    private static String[] nombres = {"Mari Pili", "Julian", "Luisa", "Paco", "Oswaldo", "Margarita"};
    
   private static Long newUserId(){
	users++;
	return users + USER_ID_INIT_VALUE;
    }
    
    public static UserDTO generateUser(){
	UserDTO newUser = new UserDTO();
	
	Long userId = newUserId();
	newUser.setId(userId);
	
	String login = "usuario" + userId;
	newUser.setLogin(login);
	newUser.setPassword(login);
	
	String email = login + "@mail.com";
	newUser.setEmail(email);
	
	String nombre = nombres[(int) (Math.random()*nombres.length)];
	newUser.setName(nombre);
	
	String apellido = apellidos[(int)(Math.random()*apellidos.length)];
	newUser.setSurname(apellido);
	
	return newUser;
    }
    
    public static UserDTO generateTestUser(){
	UserDTO newUser = new UserDTO();
	
	Long userId = newUserId();
	newUser.setId(userId);
	
	String login = "test";
	newUser.setLogin(login);
	newUser.setPassword(login);
	
	String email = login + "@mail.com";
	newUser.setEmail(email);
	
	String nombre = "Name";
	newUser.setName(nombre);
	
	String apellido = "Surname";
	newUser.setSurname(apellido);
	
	return newUser;
    }
}

