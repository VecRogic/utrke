export interface Season {
  seasonYear: string;
}

export interface Race {
  round: string;
  raceName: string;
}

export interface Driver {
  driverId: string;
  firstAndLastName: string;
}

export interface Constructor {
  constructorId: string;
  name: string;
}

export interface Time {
  time: string;
}

export interface AverageSpeed {
  speed: string;
}

export interface RaceResult {
  number: string;
  position: string;
  points: number | null;
  driver: Driver;
  constructor: Constructor;
  time?: Time;
  averageSpeed?: AverageSpeed;
}

export interface RaceResultsRequest {
  seasonYear: string;
  round: string;
}

export interface DriverData {
  driver: DriverDetails;
  races: RaceDetails[];
}

export interface DriverDetails {
  driverId:string,
  firstAndLastName: string;
  code: string;
  dateOfBirth: string;
  nationality: string;
  url: string;
}

export interface RaceDetails {
  raceName: string;
  url: string;
  season: string;
  round: string;
  date: string;
  circuit: Circuit;
}

export interface Circuit {
  circuitId:string,
  circuitName: string;
  url: string;
  location: Location;
}
export interface Location{
  locality:string,
  country:string
}
export interface ConstructorDetails {
  nationality: string;
  drivers: DriverData[];
}
