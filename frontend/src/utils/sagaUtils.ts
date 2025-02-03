import { call, put } from "redux-saga/effects";

export default function* sagaUtility(
  payload: any,
  reqMethod: any,
  successAction: any,
  failureAction: any
): any {
  try {
    const response = yield call(reqMethod, payload);
    yield processResponse(response.data, successAction, payload);
  } catch (e) {
    yield processError(e, failureAction, payload);
  }
}
function* processResponse(responseData: any, successAction: any, payload: any) {
  if (responseData) yield put(successAction(responseData));
  else {
    yield put(successAction());
  }
}

function* processError(error: any, failureAction: any, payload: any) {
  const errorData = {
    message: error.message,
    status: error.response ? error.response.status : null,
    data: error.response ? error.response.data : null,
  };
  yield put(failureAction(errorData));
}
