package fitnessapp.tracker.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "exercises")
public class Exercise {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(foreign = true)
    private Training parent;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Workload> workloads;

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

    public Training getParent() {
        return parent;
    }

    public void setParent(Training parent) {
        this.parent = parent;
    }

    public ForeignCollection<Workload> getWorkloads() {
        return workloads;
    }

    public void setWorkloads(ForeignCollection<Workload> workloads) {
        this.workloads = workloads;
    }
}
