import createSagaMiddleware from 'redux-saga';
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import { configureStore } from '@reduxjs/toolkit';
import { rootReducer, rootSaga } from '.'; 

const sagaMiddleware = createSagaMiddleware();

const middlewares = [sagaMiddleware];
const persistConfig = {
  key: 'root',
  version: 1,
  storage,
  blacklist: [],
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: ['persist/PERSIST'],
      },
    }).concat(middlewares),
});

sagaMiddleware.run(rootSaga);

export default store;
