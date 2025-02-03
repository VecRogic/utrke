import { RaceResultsRequest } from "../models/SearchRace";
import { getAxiosInstance } from "../utils/axiosUtils"

export default class SearchRaceService{

    static getListOfYears(){
       return getAxiosInstance().get(`http://localhost:8080/seasons/get-all`);
    }

    static getRacesBySelectedSeason(selectedSeason:string){
        return getAxiosInstance().get(`http://localhost:8080/races/get-all-by-season?seasonYear=${selectedSeason}`)
    }

    static getRaceResults(raceResultsForm: RaceResultsRequest){
        return getAxiosInstance().get(`http://localhost:8080/race-results/get-by-season-and-round?seasonYear=${raceResultsForm.seasonYear}&round=${raceResultsForm.round}`);
    }

    static getDriverDetails(driverId:string){
         return getAxiosInstance().get(`http://localhost:8080/races/get-by-driver-id?driverId=${driverId}`);
    }
    static getConstructorDetails(constructorId:string){
        return getAxiosInstance().get(`http://localhost:8080/get-constructor-details/${constructorId}`);
   }
}