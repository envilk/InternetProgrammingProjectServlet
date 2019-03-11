package es.unex.giiis.pi.model;

public class SignInUpValidate {

	public static boolean validateLogInEmail() {
		/* 
		 * COMPROBAR SI ESTA EN EL JSON
		 */
		return true;
	}
	
	public static boolean validateSignInPass() {
		/*
		 * COMRPOBAR SI COINCIDE CON LA PASS DE LA CUENTA GUARDADA EN EL JSON
		 */
		return true;
	}
	
	public static boolean validateSignUpPasswords(String pass1, String pass2) {
		if(pass1.equals(pass2))
			return true;
		else
			return false;
	}
	
	/*
	 * VE PONIENDO LOS QUE SE TE VAYAN OCURRIENDO
	 */
	
}
