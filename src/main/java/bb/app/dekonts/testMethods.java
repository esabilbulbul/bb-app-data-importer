/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.app.dekonts;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 *
 * @author Administrator
 */
public final class testMethods 
{
    public static String argonHash(String pPwdClean)
    {
            //String sPwdNSaltData = pEmail.trim().substring(0, 20) + pRegDate.trim() + pPwdClean.trim(); // Currently Argon2 manages salting data itself. Therefore these won't be needed
            String sPwdNSaltData = pPwdClean;

            // Create an Argon2 instance
            Argon2 argon2 = Argon2Factory.create();

            // Password to be hashed
            String password = sPwdNSaltData;//"yourPassword123";

            // Argon2 parameters
            int iterations = 3;
            int memory = 65536;
            int parallelism = 1;

            try 
            {
                // Hash the password
                String hash = argon2.hash(iterations, memory, parallelism, password);
                System.out.println("Hashed password: " + hash);

                // Verifying the password
                //boolean matches = argon2.verify(hash, password);
                //System.out.println("Password verification result: " + matches);

                return hash;
            }
            finally 
            {
                argon2.wipeArray(password.toCharArray());
            }
    }
}
