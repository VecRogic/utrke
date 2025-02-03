import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { setSelectedRace as setSelectedRaceAction, getRacesBySeasonRequest } from "../../store/searchRace/reducer";
import { useAppSelector } from "../../store";
import { Race } from "../../models/SearchRace";
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

function getStyles(name: string | number, selected: string | number | null, theme: Theme) {
  return {
    fontWeight:
      selected === name
        ? theme.typography.fontWeightMedium
        : theme.typography.fontWeightRegular,
  };
}

const RacePicker = () => {
  const dispatch = useDispatch();
  const theme = useTheme();
  const races = useAppSelector((state) => state.searchRace.races) ?? []; 
  const selectedYear = useAppSelector((state) => state.searchRace.selectedSeason) ?? undefined;


  useEffect(() => {
    if (selectedYear) {
      dispatch(getRacesBySeasonRequest(selectedYear));
    }
  }, [selectedYear]);

  const [localSelectedRace, setLocalSelectedRace] = useState<string | number | null>(null);

  const handleRaceSelect = (event: SelectChangeEvent<string | number>) => {
    const selected = event.target.value as string | number;
    setLocalSelectedRace(selected);
  };

  useEffect(() => {
    if (localSelectedRace) {
      dispatch(setSelectedRaceAction(localSelectedRace)); 
    }
  }, [localSelectedRace, dispatch]);

  return (
    <div>
      <h2>Race Picker</h2>
      <FormControl sx={{ m: 1, width: 300 }}>
        <InputLabel id="race-picker-label">Race</InputLabel>
        <Select
          labelId="race-picker-label"
          id="race-picker"
          value={localSelectedRace || ""}
          onChange={handleRaceSelect}
          input={<OutlinedInput label="Race" />}
          MenuProps={MenuProps}
        >
          {Array.isArray(races) && races.length > 0 ? (
            races.map((race: Race) => (
              <MenuItem
                key={race.round}
                value={race.round}
                style={getStyles(race.round, localSelectedRace, theme)}
              >
                {race.raceName}
              </MenuItem>
            ))
          ) : (
            <MenuItem disabled>No races available</MenuItem>
          )}
        </Select>
      </FormControl>
    </div>
  );
};

export default RacePicker;
