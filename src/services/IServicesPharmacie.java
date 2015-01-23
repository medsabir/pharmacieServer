package services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import dao.Pharmacie;

@WebService
public interface IServicesPharmacie {

	@WebMethod
	@WebResult(name="ListeDesPharmacies")
	public List<Pharmacie> ListdesPharmacies();

	@WebMethod
	@WebResult(name="ListeDesPharmaciesEnGarde")
	public List<Pharmacie> ListdesPharmaciesenGarde();

	@WebMethod
	@WebResult(name="ListeDesPharmaciesProche")
	public List<Pharmacie> ListdesPharmaciesProche(@WebParam(partName="Latitude")Double lat,@WebParam(partName="Longitude")Double log) throws InvalidInputException;

}