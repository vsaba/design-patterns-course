package hr.fer.ooup.lab3.zad2.location;

import java.util.Comparator;
import java.util.Objects;

public class LocationRange {


    private Location locationStart;
    private Location locationEnd;


    public LocationRange() {
        this.locationStart = null;
        this.locationEnd = null;
    }

    public LocationRange(Location locationStart, Location locationEnd) {
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
    }

    public Location getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(Location locationStart) {
        this.locationStart = locationStart;
    }

    public Location getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(Location locationEnd) {
        this.locationEnd = locationEnd;
    }

    public boolean hasSelection() {

        if (locationStart == null && locationEnd == null) {
            return false;
        }

        return true;
    }

    public LocationRange sortSelection() {
        if (!hasSelection()) {
            return this;
        }

        LocationRange range = new LocationRange();

        Comparator<Location> locationComparator = new Comparator<Location>() {
            @Override
            public int compare(Location o1, Location o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (o2.getRow() - o1.getRow() != 0) {
                    return o2.getRow() - o1.getRow();
                } else {
                    return o2.getCol() - o1.getCol();
                }
            }
        };


        int comp = locationComparator.compare(locationStart, locationEnd);

        if (comp == 0) {
            return this;
        } else if (comp < 0) {
            range.setLocationStart(this.locationEnd);
            range.setLocationEnd(this.locationStart);
        } else {
            range.setLocationStart(this.locationStart);
            range.setLocationEnd(this.locationEnd);
        }

        return range;

    }

    public void resetSelection() {
        locationStart = null;
        locationEnd = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationRange that = (LocationRange) o;
        return Objects.equals(locationStart, that.locationStart) && Objects.equals(locationEnd, that.locationEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationStart, locationEnd);
    }

    @Override
    public String toString() {
        return "LocationRange{" +
                "locationStart=" + locationStart +
                ", locationEnd=" + locationEnd +
                '}';
    }
}
