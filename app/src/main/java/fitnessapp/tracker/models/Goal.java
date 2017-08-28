package fitnessapp.tracker.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Goal {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private String name;

    @ForeignCollectionField(eager = true)
    ForeignCollection<Purpose> purposes;

    @DatabaseField()
    private long till;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Purpose> getPurposes() {
        return purposes;
    }

    public void setPurposes(ForeignCollection<Purpose> purposes) {
        this.purposes = purposes;
    }

    public long getTill() {
        return till;
    }

    public void setTill(long till) {
        this.till = till;
    }
}
