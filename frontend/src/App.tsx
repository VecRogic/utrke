import "./App.css";
import { BrowserRouter } from "react-router-dom";

import { Provider } from "react-redux";
import store from "./store/configureStore";
import { PersistGate } from "redux-persist/integration/react";
import { persistStore } from "redux-persist";

import React from "react";
import Layout from "./components/core/Layout";
import { injectStore } from "./utils/axiosUtils";

//https://github.com/rt2zz/redux-persist
let persist = persistStore(store);
injectStore(store);
function App() {
  return (
    <Provider store={store}>
      <PersistGate loading={null} persistor={persist}>
        <BrowserRouter>
          <Layout />
        </BrowserRouter>
      </PersistGate>
    </Provider>
  );
}

export default App;
