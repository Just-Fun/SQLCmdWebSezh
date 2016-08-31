package ua.com.juja.sqlcmd.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataSetImpl implements DataSet {

    private HashMap map;

    public DataSetImpl() {
        map = new HashMap<>();
    }

    @Override
    public void put(String name, Object value) {
        map.put(name, value);
    }

    @Override
    public List<Object> getValues() {
        List result = new ArrayList<>();
        result.addAll(map.values());
        return result;
    }

    @Override
    public Set<String> getNames() {
        return map.keySet();
    }

    @Override
    public Object get(String name) {
        return map.get(name);
    }

    @Override
    public void updateFrom(DataSet newValue) {
        for (String name : this.getNames()) {
            map.put(name, newValue.get(name));
        }
    }

    @Override
    public String toString() {
        return String.format("{names:%s, value: %s}",
                getNames(), getValues());
    }
}
