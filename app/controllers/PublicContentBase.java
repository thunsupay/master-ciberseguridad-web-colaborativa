package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;

public class PublicContentBase extends Controller {

    public static void register(boolean usernameExists, String usernameValidated){
        /*Adición de variable tipo boolean para verificar
         *la existencia de un usuario en la Form Registro */
        renderArgs.put("usernameValidated", usernameValidated);
        renderArgs.put("usernameExists", usernameExists);
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
        User u = new User(username, HashUtils.getMd5(password), type, -1);

        /*Condición para verificar si un usuario
         *existe */
        boolean usernameExits = u.usernameExists();
        if(!usernameExits){
            u.save();
            registerComplete();
        } else{
            /* En caso existe recargar Form Registro
             * Con el indicador en true */
            register(usernameExits, username);
        }        
    }

    public static void registerComplete(){
        render();
    }
}
