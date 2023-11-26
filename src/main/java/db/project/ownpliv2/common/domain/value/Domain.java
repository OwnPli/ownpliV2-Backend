package db.project.ownpliv2.common.domain.value;

public enum Domain {

    ALBUM, ARTIST, TRACK;


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
