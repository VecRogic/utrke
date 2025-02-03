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
import { useParams } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../store";
import { getDriverDetailsRequest } from "../../store/searchRace/reducer";
import FullScreenLoader from "../shared/FullScreenLoader";

const DriverDetails = () => {
  const dispatch = useAppDispatch();
  const { id } = useParams();
  const loading = useAppSelector((state) => state.searchRace.loading);
  const driverDetails =
    useAppSelector((state) => state.searchRace.driverDetails) ?? undefined;

  useEffect(() => {
    if (id) dispatch(getDriverDetailsRequest(id));
  }, [id]);

  return (
    <>
      {loading && <FullScreenLoader />}
      {driverDetails ? (
        <Box sx={{ p: 4 }}>
          {/* Driver Details Card */}
          <Card sx={{ mb: 4, boxShadow: 3 }}>
            <CardContent>
              <Typography variant="h5" component="div" gutterBottom>
                {driverDetails.driver.firstAndLastName}
              </Typography>
              <Typography
                variant="subtitle1"
                color="text.secondary"
                gutterBottom
              >
                Code: {driverDetails.driver.code}
              </Typography>
              <Typography variant="body1">
                <strong>Date of Birth:</strong>{" "}
                {new Date(driverDetails.driver.dateOfBirth).toLocaleDateString()}
              </Typography>
              <Typography variant="body1">
                <strong>Nationality:</strong> {driverDetails.driver.nationality}
              </Typography>
            </CardContent>
            <CardActions>
              <Button
                size="small"
                variant="contained"
                color="primary"
                href={driverDetails.driver.url}
                target="_blank"
              >
                View Wikipedia
              </Button>
            </CardActions>
          </Card>
          <Typography variant="h4" gutterBottom>
            Race Details
          </Typography>
          {driverDetails.races?.length > 0 ? (
            <Grid container spacing={3}>
              {driverDetails.races.map((race, index) => (
                <Grid item xs={12} md={6} key={index}>
                  <Card sx={{ boxShadow: 2 }}>
                    <CardContent>
                      <Typography variant="h6" component="div">
                        {race.raceName}
                      </Typography>
                      <Typography variant="body1" color="text.secondary">
                        <strong>Season:</strong> {race.season},{" "}
                        <strong>Round:</strong> {race.round}
                      </Typography>
                      <Typography variant="body2">
                        <strong>Date:</strong>{" "}
                        {new Date(race.date).toLocaleDateString()}
                      </Typography>
                      <Typography variant="body2">
                        <strong>Location:</strong> {race.circuit.location.locality}, {race.circuit.location.country}
                      </Typography>
                      <Typography variant="body2">
                        <strong>Circuit:</strong>{" "}
                        <a
                          href={race.circuit.url}
                          target="_blank"
                          rel="noopener noreferrer"
                        >
                          {race.circuit.circuitName}
                        </a>
                      </Typography>
                    </CardContent>
                    <CardActions>
                      <Button
                        size="small"
                        variant="outlined"
                        color="secondary"
                        href={race.url}
                        target="_blank"
                      >
                        View Race
                      </Button>
                    </CardActions>
                  </Card>
                </Grid>
              ))}
            </Grid>
          ) : (
            <Typography variant="body1" color="text.secondary" sx={{ mt: 2 }}>
              No races found for this driver.
            </Typography>
          )}
        </Box>
      ) : (
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            height: "100vh",
            textAlign: "center",
            backgroundColor: "#f9f9f9",
            p: 4,
          }}
        >
          <Typography variant="h5" color="text.secondary" gutterBottom>
            No Data Available
          </Typography>
          <Typography variant="body1" color="text.secondary" sx={{ mb: 2 }}>
            We couldn’t find any details for the selected driver.
          </Typography>
          <Button
            variant="contained"
            color="primary"
            onClick={() => window.history.back()}
          >
            Go Back
          </Button>
        </Box>
      )}
    </>
  );
};

export default DriverDetails;
