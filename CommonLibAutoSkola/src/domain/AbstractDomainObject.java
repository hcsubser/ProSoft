/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mmmdeb
 */
public interface AbstractDomainObject extends Serializable{
    
    String getTableName();
    
    String getColumnNamesForInsert();
    
    String getInsertValues();
    
    String getUpdateValues();
    
    String getPrimaryKeyValue();
    
    String alias();
    
    String join();
    
    String condition();
        
    List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException;

}
