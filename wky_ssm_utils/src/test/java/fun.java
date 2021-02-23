import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class fun {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        UserService userService = new UserService();
        System.out.println(userService);


        Class<? extends UserController> clazz = userController.getClass();
        Field service = clazz.getDeclaredField("userService");
        service.setAccessible(true);

        String name = service.getName();
        name = name.substring(0,1).toUpperCase()+name.substring(1,name.length());
        String methodName = "set"+name;
        Method method = clazz.getMethod(methodName, UserService.class);
        method.invoke(userController,userService);
        System.out.println(userController.getUserService());

    }
}
