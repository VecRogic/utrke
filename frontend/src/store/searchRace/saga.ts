import { call, ForkEffect, put, select, takeLatest } from "redux-saga/effects";
import { PayloadAction } from "@reduxjs/toolkit";

import sagaUtility from "../../utils/sagaUtils";
import SearchRaceService from "../../services/SearchRaceService";
import {
  getListOfYearSuccess,
  getListOfYearFailure,
  getListOfYearRequest,
  getRacesBySeasonRequest,
  getRacesBySeasonSuccess,
  getRacesBySeasonFailure,
  getRaceResultSuccess,
  getRaceResultFailure,
  getRaceResultsRequest,
  getDriverDetailsSuccess,
  getDriverDetailsFailure,
  getDriverDetailsRequest,
  getConstructorDetailsSuccess,
  getConstructorDetailsFailure,
  getConstructorDetailsRequest
} from "./reducer";

function* getListOfYearRequestGen() {
  yield sagaUtility(
    null,
    SearchRaceService.getListOfYears,
    getListOfYearSuccess,
    getListOfYearFailure
  );
}
function* getRacesBySeasonRequestGen(action: PayloadAction<any>) {
  yield sagaUtility(
    action.payload,
    SearchRaceService.getRacesBySelectedSeason,
    getRacesBySeasonSuccess,
    getRacesBySeasonFailure
  );
}
function* getRaceResultsRequestGen(action: PayloadAction<any>) {
  yield sagaUtility(
    action.payload,
    SearchRaceService.getRaceResults,
    getRaceResultSuccess,
    getRaceResultFailure
  );
}
function* getDriverDetailRequestGen(action: PayloadAction<any>) {
  yield sagaUtility(
    action.payload,
    SearchRaceService.getDriverDetails,
    getDriverDetailsSuccess,
    getDriverDetailsFailure
  );
}
function* getConstructorDetailsRequestGen(action: PayloadAction<any>) {
  yield sagaUtility(
    action.payload,
    SearchRaceService.getConstructorDetails,
    getConstructorDetailsSuccess,
    getConstructorDetailsFailure
  );
}
export default function* searchRaceSaga(): Generator<ForkEffect<never>, void> {
  yield takeLatest(getListOfYearRequest.type, getListOfYearRequestGen);
  yield takeLatest(getRacesBySeasonRequest.type, getRacesBySeasonRequestGen);
  yield takeLatest(getRaceResultsRequest.type, getRaceResultsRequestGen);
  yield takeLatest(getDriverDetailsRequest.type, getDriverDetailRequestGen);
  yield takeLatest(getConstructorDetailsRequest.type, getConstructorDetailsRequestGen);
}
