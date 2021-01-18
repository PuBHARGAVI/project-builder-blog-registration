package businesslogic;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
public class RegistrationValidation{
	public boolean checkUserDetails(String email, String password, String confirmPassword) {
		boolean validemail=validEmail(email);
		boolean validpassword=validPassword(password,confirmPassword);
		return validemail&&validpassword;
	}
	private boolean validPassword(String password, String confirmPassword) {
		int uc=0,lc=0,nu=0;
		if(password.isEmpty()) {
			return false;
		}
		if(!password.equals(confirmPassword)) {
			return false;
		}
		if(password.length()<8 || password.length()>20) {
			return false;
		}
		for(int i=0;i<password.length();i++) {
			if(lc>=1 && uc>=1 && nu>=1) {
				break;
			}
			if(password.charAt(i)>='a' && password.charAt(i)<='z') {
				lc=lc+1;
			}
			else if(password.charAt(i)>='A' && password.charAt(i)<='Z') {
				uc=uc+1;
			}
			else if(Character.isDigit(password.charAt(i))) {
				nu+=1;
			}
		}
		if(lc==0 || uc==0 || nu==0) {
			return false;
		}
		return true;
	}
	private boolean validEmail(String email) {
		String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
        Pattern pat = Pattern.compile(emailReg); 
        if(email==null) { 
        	return false;
        }
        return pat.matcher(email).matches(); 
	}
}