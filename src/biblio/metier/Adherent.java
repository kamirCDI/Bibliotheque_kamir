/**
 * 
 */
package biblio.metier;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author afpa
 *
 */
public class Adherent extends Utilisateur{

	private String telephone;
	private static int nbMaxPrets=3;
	private static int dureeMaxPrets=15;
	
	
	public Adherent(String nom, String prenom, Date dateNaissance, String sexe, int id, String pwd, String pseudonyme, String telephone) throws ParseException{
		super(nom, prenom, dateNaissance, sexe, id, pwd, pseudonyme);
		setTelephone(telephone);
	}
	
	public Adherent() throws ParseException{
		this("Devanne", "camille", sdf.parse("02/05/1970"), "feminin", 3, "kamille", "foufou", "0102253563");
	}

	/***************************Getters et Setters*******************************************/
	
	public String getTelephone() {
		return telephone;
	}

	

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/********************************classes métier*********************************************/
	public Boolean isConditionsPretAcceptees(){
		if(getNbEmpruntsEnCours()>=3 || getNbRetards()!=0)
			return false;
		else	
			return true;
	}
	
	

	public int getNbRetards(){
		int nb=0;
		for(EmpruntEnCours s:getEmpruntEnCours())
		{
			if(s.isPretEnRetard())
				nb++;
		}
		return nb;
	}
	
	
	public static int getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public static void setDureeMaxPrets(int dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}
	
	@Override
	public void addEmpruntEnCours(EmpruntEnCours unEmpruntEnCours) throws BiblioException {
		if (isConditionsPretAcceptees())
			super.addEmpruntEnCours(unEmpruntEnCours);
		else 
			throw new BiblioException("Les conditions pour un nouveau prêt ne sont pas satisfaites");
		
	}

	@Override
	public String toString() {
		return super.toString() + "Adherent [telephone=" + telephone + "]";
	}

}
