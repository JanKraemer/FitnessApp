package fitnessapp.tracker.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;

@DatabaseTable( tableName = "trainings" )
public class Training {

    @DatabaseField( generatedId = true )
    private Integer id;

    @DatabaseField( canBeNull = false )
    private long date;

    @ForeignCollectionField( eager = true )
    private ForeignCollection< Exercise > exercises;

    @DatabaseField( canBeNull = false )
    private TrainingsType type;

    public long getDate( ) {
        return date;
    }

    public void setDate( long date ) {
        this.date = date;
    }

    public ForeignCollection< Exercise > getExercises( ) {
        return exercises;
    }

    public void setExercises( ForeignCollection< Exercise > exercises ) {
        this.exercises = exercises;
    }

    public TrainingsType getType( ) {
        return type;
    }

    public void setType( TrainingsType type ) {
        this.type = type;
    }

    public Integer getId( ) {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public boolean wasToday( ) {
        return getTodayAsLong( ) < date;
    }

    public boolean wasYesterday( ) {
        final Calendar cal = midnightOnCalendar( );
        cal.add( Calendar.DATE, -1 );
        return cal.getTimeInMillis( ) < date;
    }

    public long getTodayAsLong( ) {
        final Calendar cal = midnightOnCalendar( );
        return cal.getTimeInMillis( );
    }

    private Calendar midnightOnCalendar( ) {
        final Calendar cal = Calendar.getInstance( );
        cal.set( Calendar.HOUR, 0 );
        cal.set( Calendar.MINUTE, 0 );
        return cal;
    }
}
