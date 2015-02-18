package crawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MyPageParser {

	static Connection connection = SingletonConnection.getConnection();
	
	private static String transform(String s)
	{
		StringBuffer temp = new StringBuffer();
		int n = s.length();
		for (int i = 0; i < n; i++)
		{
			if (s.charAt(i) =='\'')
			{
				temp.append("\'\'");
			}
			else
			{
				temp.append(s.charAt(i));
			}

			if (s.charAt(i) ==' ')
			{
				temp.append("");
			}
			else
			{
				temp.append(s.charAt(i));
			}
		}
		return temp.toString();
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		int i=0;
		String langitude= null;
		String latitude= null;
		
		PreparedStatement ps = connection.prepareStatement("truncate pharmacie;");	
		ps.executeQuery();
			
		//On se connecte au site et on charge le document html
		Document doc = Jsoup.connect("http://www.anahna.com/pharmacies-agadir-ca7-qa0.html").timeout(10*1000).get();

		//On récupère dans ce document la premiere balise ayant comme nom div et pour attribut class="right"
		Elements links = doc.select("div .right"); 
		for(Element link: links){	
			String  adress=  link.child(1).text();
			transform(adress);

			String tel =  link.child(2).text();
			transform(tel);

			String nomPharmacie =  link.child(0).text();
			transform(nomPharmacie);

			Elements shs = doc.select("div .left"); 
			
			Element maps = shs.select("a").get(i); 

			String absHref = maps.attr("abs:href"); // "http://jsoup.org/"

			    //trouver coordonner depuis script
				Document docs = Jsoup.connect(absHref).timeout(10000).get();
				//on vise le dernier tag  'script'de la page
				Element scriptElement = docs.select("script").last();

				//charger tout le script
				String jsCode = scriptElement.html();
			    //System.out.println(jsCode);
				
			    //extraire la ligne qui nous interesse,dans ce cas les coordonées de la pharmacie
			    jsCode = jsCode.substring(jsCode.indexOf('['),jsCode.indexOf(']'));	
			    //System.out.println(jsCode);
			    
			    //il existe quelque pharmacies sont coordonnées qui declanche une exeption dans le traitement
			    if (jsCode.length()> 6)
			    {
			     latitude = jsCode.substring(1,jsCode.indexOf(','));
			     langitude = jsCode.substring(jsCode.indexOf(','));		    
			     langitude = langitude.substring(2);
			    }

			    insertToTable(nomPharmacie, tel, adress, absHref, latitude, langitude, false);

			i++;
		}
			
			//trouver les pharmacies de garde depuis http://www.blanee.com
			Document gard = Jsoup.connect("http://www.blanee.com/guides/pharmacies-de-garde-a-agadir-du-24-au-30-decembre-agadir").timeout(10*1000).get();

			//On récupère dans ce document la premiere balise ayant comme nom div et pour attribut class="info"
			Elements gos = gard.select("div .info"); 
			for(Element go: gos){
				//on récupère la balise a [href] qui contient les noms des pharmacies de gardes
				String nomPharmacie2 = go.select("a[href]").get(0).text();
				
				//on récupère la balise a [href] qui contient les urls des cordonnées lat et long
				String urlgard = go.select("a").attr("href");
				//System.out.println(urlgard);
				
				//trouver coordonner depuis script
				Document phppage = Jsoup.connect("http://www.blanee.com"+urlgard).timeout(10000).get();
				
				//on recupere l'element adress de la pharmacie	
				Element addr = phppage.select("li").get(12);
				//System.out.println(addr);
				String adress2 = addr.text();
				transform(adress2);
				
				//on recupere l'element telephone de la pharmacie	
				Element telp = phppage.select("li").get(15);
				//System.out.println(addr);
				String tell = telp.text();
				transform(tell);
				
				//on vise le dernier tag  'script'de la page
				Element tagscript = phppage.select("script").get(7);
				
				//charger tout le script
				String jsCode1 = tagscript.html();
				//System.out.println(jsCode);
				
				if(jsCode1.length()>100){ 
				
				latitude = jsCode1.substring(20,jsCode1.indexOf(','));
				langitude = jsCode1.substring(jsCode1.indexOf(','));		    
				langitude = langitude.substring(2,16);

				}else{
					latitude = "30.4007";
					langitude ="-9.59484";
				}

				insertToTable(nomPharmacie2, tell, adress2, urlgard, latitude,langitude, true);
		}
		System.out.println("done");
	}
	
	private static void insertToTable(String nom, String tel, String adresse, String url, String latitude, String longitude, boolean garde) throws SQLException{
		String sql = null;
		if(garde==true){
			sql = "insert into pharmacie (NOM,NUM,ADRESSE,URL,LATITUDE,LONGITUDE,GARDE)" + "values(?,?,?,?,?,?,'true')";
		}else sql = "insert into pharmacie (NOM,NUM,ADRESSE,URL,LATITUDE,LONGITUDE,GARDE)" + "values(?,?,?,?,?,?,'false')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nom);
		preparedStatement.setString(2, tel);
		preparedStatement.setString(3, adresse);
		preparedStatement.setString(4, url);
		preparedStatement.setString(5, latitude);
		preparedStatement.setString(6, longitude);
		preparedStatement.executeUpdate();
	}
}




