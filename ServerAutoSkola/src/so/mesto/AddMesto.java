/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import dbbroker.DBBroker;
import domain.AbstractDomainObject;
import domain.Mesto;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author mmmdeb
 */
public class AddMesto extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Mesto)) {
            throw new Exception("Prosledjeni objekat nije instanca trazene klase!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
   
    
}
