import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import {
    ConstructorDetails,
  DriverData,
  Race,
  RaceResult,
  Season,
} from "../../models/SearchRace";


export interface RaceSearchState {
  seasons?: Season[];
  loading: boolean;
  error: any;
  races?: Race[];
  selectedSeason?: string;
  selectedRace?: string;
  raceResults?: RaceResult[];
  driverDetails?: DriverData;
  constructorDetails?:ConstructorDetails;
}

const initialState: RaceSearchState = {
  seasons: undefined,
  loading: false,
  error: undefined,
  races: undefined,
  selectedSeason: undefined,
  selectedRace: undefined,
  driverDetails: undefined,
  constructorDetails:undefined
};
//https://redux-toolkit.js.org/api/createSlice
const searchRaceSlice = createSlice({
  name: "searchRace",
  initialState,
  reducers: {
    getListOfYearRequest: (state: RaceSearchState) => {
      state.loading = true;
    },
    getListOfYearSuccess: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = undefined;
      state.seasons = action.payload;
    },
    getListOfYearFailure: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = action.payload;
    },
    setSelectedSeason(state: RaceSearchState, action: PayloadAction<any>) {
      state.selectedSeason = action.payload;
    },
    getRacesBySeasonRequest: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = true;
    },
    getRacesBySeasonSuccess: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = undefined;
      state.races = action.payload;
    },
    getRacesBySeasonFailure: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = action.payload;
    },
    setSelectedRace: (
      state: RaceSearchState,
      action: PayloadAction<string | number>
    ) => {
      state.selectedRace = action.payload.toString();
    },
    getRaceResultsRequest: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = true;
    },
    getRaceResultSuccess: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = undefined;
      state.raceResults = action.payload;
    },
    getRaceResultFailure: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = action.payload;
    },
    getDriverDetailsRequest: (
      state: RaceSearchState,
      action: PayloadAction<string>
    ) => {
      state.loading = true;
    },
    getDriverDetailsSuccess: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = undefined;
      state.driverDetails = action.payload;
    },
    getDriverDetailsFailure: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.driverDetails = undefined;
      state.error = action.payload;
    },
    setDriverDetails: (state: RaceSearchState, action: PayloadAction<any>) => {
      state.driverDetails = action.payload;
    },
    getConstructorDetailsRequest: (
      state: RaceSearchState,
      action: PayloadAction<string>
    ) => {
      state.loading = true;
    },
    getConstructorDetailsSuccess: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.error = undefined;
      state.constructorDetails = action.payload;
    },
    getConstructorDetailsFailure: (
      state: RaceSearchState,
      action: PayloadAction<any>
    ) => {
      state.loading = false;
      state.constructorDetails = undefined;
      state.error = action.payload;
    },
    setConstructorDetails: (state: RaceSearchState, action: PayloadAction<any>) => {
      state.constructorDetails = action.payload;
    }
  },
});

export const {
  getListOfYearRequest,
  getListOfYearSuccess,
  getListOfYearFailure,
  setSelectedSeason,
  getRacesBySeasonFailure,
  getRacesBySeasonRequest,
  getRacesBySeasonSuccess,
  setSelectedRace,
  getRaceResultFailure,
  getRaceResultSuccess,
  getRaceResultsRequest,
  getDriverDetailsRequest,
  getDriverDetailsSuccess,
  getDriverDetailsFailure,
  setDriverDetails,
  getConstructorDetailsFailure,
  getConstructorDetailsRequest,
  getConstructorDetailsSuccess,
  setConstructorDetails
} = searchRaceSlice.actions;
export default searchRaceSlice.reducer;
