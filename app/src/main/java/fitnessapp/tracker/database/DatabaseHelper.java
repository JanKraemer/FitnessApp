package fitnessapp.tracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import fitnessapp.tracker.models.Goal;
import fitnessapp.tracker.models.Training;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "fitness.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;
    // create a log name
    private final String LOG_NAME = getClass().getName();
    // the DAO object we use to access the SimpleData table
    private RuntimeExceptionDao<Goal, Integer> goalsDao = null;
    private RuntimeExceptionDao<Training, Integer> trainingDao = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTableIfNotExists(connectionSource,Goal.class);
        }catch (SQLException e){
            Log.e(LOG_NAME,"Could not create database tables.",e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.createTableIfNotExists(connectionSource,Goal.class);
        }catch (SQLException e){
            Log.e(LOG_NAME,"Could not update database Tables.",e);
        }
    }

    public Dao<Goal,Integer> getGoalDao() throws SQLException{
        if(goalsDao == null)
        {
            goalsDao = getDao(Goal.class);
        }
        return goalsDao;
    }

    public Dao<Training,Integer> getTrainingDao() throws SQLException{
        if(trainingDao == null)
        {
            trainingDao = getDao(Training.class);
        }
        return trainingDao;
    }


    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        goalsDao = null;
    }
}
