import axios, { AxiosInstance } from "axios";

let store: any;

export const injectStore = (_store: any) => {
  store = _store;
};

const { CancelToken } = axios;
const timeout = 60 * 1000;

let axiosInstance: AxiosInstance;

export function getAxiosInstance(): AxiosInstance {
  if (axiosInstance === null || axiosInstance === undefined) {
    axiosInstance = getNewAxiosInstance();
  }
  //@ts-ignore
  axiosInstance.cancelToken = new CancelToken((cancel) =>
    setTimeout(() => cancel("Error"), timeout)
  );
  return axiosInstance;
}

function getNewAxiosInstance() {
  axiosInstance = axios.create({
    timeout,
  });
  return setupInterceptors();
}

function setupInterceptors() {
  axiosInstance.interceptors.request.use(
    async (config) => {
      return config;
    },
    (error) => {
      return Promise.reject(new Error(error));
    }
  );

  axiosInstance.interceptors.response.use(
    (response) => response,
    async (error) => {
      if (error.response) {
        const { status, data } = error.response;

        if (status === 400) {
          return Promise.reject(new Error("Bad Request: " + (data.message || data)));
        } else if (status === 401) {
          return Promise.reject(new Error("Unauthorized: " + (data.message || "Please log in again.")));
        } else if (status === 500) {
          return Promise.reject(new Error("Internal Server Error: " + (data.message || data)));
        } else {
          return Promise.reject(new Error("An error occurred: " + (data.message || data)));
        }
      } else if (error.message) {
        return Promise.reject(new Error(error.message));
      } else {
        return Promise.reject(new Error("An unexpected error occurred."));
      }
    }
  );
  //@ts-ignore
  axiosInstance.spread = axios.spread;
  return axiosInstance;
}
