import React, { useEffect } from "react";
import {
  Card,
  CardContent,
  CardActions,
  Typography,
  Button,
  Box,
  Grid,
} from "@mui/material";
import { useAppDispatch, useAppSelector } from "../../store";
import { useParams } from "react-router-dom";
import { getConstructorDetailsRequest } from "../../store/searchRace/reducer";

// Mock Data for Constructor and Drivers
const constructor = {
  constructorId: "red_bull",
  url: "http://en.wikipedia.org/wiki/Red_Bull_Racing",
  name: "Red Bull",
  nationality: "Austrian",
};

const drivers = [
  {
    driverId: "alonso",
    code: "ALO",
    url: "http://en.wikipedia.org/wiki/Fernando_Alonso",
    givenName: "Fernando",
    familyName: "Alonso",
    nationality: "Spanish",
  },
  {
    driverId: "verstappen",
    code: "VER",
    url: "http://en.wikipedia.org/wiki/Max_Verstappen",
    givenName: "Max",
    familyName: "Verstappen",
    nationality: "Dutch",
  },
  {
    driverId: "webber",
    code: "WEB",
    url: "http://en.wikipedia.org/wiki/Mark_Webber",
    givenName: "Mark",
    familyName: "Webber",
    nationality: "Australian",
  },
];
const ConstructorDetails = () => {
  const dispatch = useAppDispatch();
  const { id } = useParams();
  const driverDetails =
    useAppSelector((state) => state.searchRace.driverDetails) ?? undefined;
  useEffect(() => {
    if (id) dispatch(getConstructorDetailsRequest(id));
  }, [id]);
  return (
    <>
      {!driverDetails ? (
        <Box sx={{ p: 4 }}>
          <Card sx={{ mb: 4, boxShadow: 3 }}>
            <CardContent>
              <Typography variant="h5" component="div" gutterBottom>
                {constructor.name}
              </Typography>
              <Typography
                variant="subtitle1"
                color="text.secondary"
                gutterBottom
              >
                Nationality: {constructor.nationality}
              </Typography>
            </CardContent>
            <CardActions>
              <Button
                size="small"
                variant="contained"
                color="primary"
                href={constructor.url}
                target="_blank"
              >
                View Wikipedia
              </Button>
            </CardActions>
          </Card>

          {/* Drivers List */}
          <Typography variant="h4" gutterBottom>
            Drivers Who Have Driven for {constructor.name}
          </Typography>
          <Grid container spacing={3}>
            {drivers.map((driver, index) => (
              <Grid item xs={12} md={4} key={index}>
                <Card sx={{ boxShadow: 2 }}>
                  <CardContent>
                    <Typography variant="h6" component="div">
                      {driver.givenName} {driver.familyName}
                    </Typography>
                    <Typography variant="body1" color="text.secondary">
                      <strong>Code:</strong> {driver.code}
                    </Typography>
                    <Typography variant="body1">
                      <strong>Nationality:</strong> {driver.nationality}
                    </Typography>
                  </CardContent>
                  <CardActions>
                    <Button
                      size="small"
                      variant="outlined"
                      color="secondary"
                      href={driver.url}
                      target="_blank"
                    >
                      View Wikipedia
                    </Button>
                  </CardActions>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Box>
      ) : (
        <div>No data</div>
      )}
    </>
  );
};

export default ConstructorDetails;
