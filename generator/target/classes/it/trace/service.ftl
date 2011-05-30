package auto.service;

import java.util.List;

import auto.dao.${name}Dao;
import auto.entity.${name};

public class ${name}Manager {

    private ${name}Dao dao = new ${name}Dao();

    public ${name} select(long id) {
    	return dao.select(id);
    }

    public List<${name}> selectAll() {
    	return dao.selectAll();
    }

    public int update(${name} ${name?uncap_first}) {
    	return dao.update(${name?uncap_first});
    }

    public int insert(${name} ${name?uncap_first}) {
    	return dao.insert(${name?uncap_first});
    }

    public int delete(long id) {
    	return dao.delete(id);
    }

}