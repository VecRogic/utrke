import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { getListOfYearRequest, setSelectedSeason } from "../../store/searchRace/reducer";
import { useAppSelector } from "../../store";
import { Season } from "../../models/SearchRace";
import { Theme, useTheme } from "@mui/material/styles";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

function getStyles(name: string, selected: string | number | null, theme: Theme) {
  return {
    fontWeight:
      selected === name
        ? theme.typography.fontWeightMedium
        : theme.typography.fontWeightRegular,
  };
}

const SeasonPicker = () => {
  const dispatch = useDispatch();
  const theme = useTheme();
  const seasons = useAppSelector((state) => state.searchRace.seasons) ?? []; 


  useEffect(() => {
    if (seasons.length === 0) {
      dispatch(getListOfYearRequest());
    }
  }, [dispatch, seasons]);

  const [selectedSeasonYear, setSelectedSeasonYear] = useState<string | number | null>(null);

  const handleSeasonSelect = (event: SelectChangeEvent<string | number>) =>{
    const selectedSeason = event.target.value as string | number;
    dispatch(setSelectedSeason(selectedSeason));
    setSelectedSeasonYear(selectedSeason);
  };

  return (
    <div>
      <h2>Season Picker</h2>
      <FormControl sx={{ m: 1, width: 300 }}>
        <InputLabel id="season-picker-label">Season</InputLabel>
        <Select
          labelId="season-picker-label"
          id="season-picker"
          value={selectedSeasonYear || ""}
          onChange={handleSeasonSelect}
          input={<OutlinedInput label="Season" />}
          MenuProps={MenuProps}
        >
          {Array.isArray(seasons) && seasons.length > 0 ? (
            seasons.map((season: Season) => (
              <MenuItem
                key={season.seasonYear}
                value={season.seasonYear}
                style={getStyles(season.seasonYear, selectedSeasonYear, theme)}
              >
                {season.seasonYear}
              </MenuItem>
            ))
          ) : (
            <MenuItem disabled>No seasons available</MenuItem>
          )}
        </Select>
      </FormControl>
    </div>
  );
};

export default SeasonPicker;
