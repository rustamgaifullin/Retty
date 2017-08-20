package com.rm.retty.api.repository;

import com.rm.retty.api.model.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractRepository<KEY, DATA extends Model<KEY>> {
    private final Map<KEY, DATA> table = new HashMap<>();

    public AbstractRepository(List<DATA> dataList) {
        dataList.forEach(data -> table.put(data.getKey(), data));
    }

    public DATA get(KEY key) {
        return table.get(key);
    }

    public void save(DATA data) {
        KEY key = data.getKey();
        table.put(key, data);
    }
}