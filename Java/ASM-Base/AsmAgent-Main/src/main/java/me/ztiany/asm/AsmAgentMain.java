package me.ztiany.asm;

/**
 * -javaagent:E:\code\studio\my_github\Repository\Java\ASM-Base\AsmAgent\build\libs\AsmAgent-1.0.jar
 *
 * @author ztiany
 * Email: ztiany3@gmail.com
 */
public class AsmAgentMain {

    public static void main(String... args) {
        System.out.println("AsmAgentMain running");
        User user = new User();
        user.setAge(12);
        user.setName("Ztiany");
        System.out.println(user);
    }

}
