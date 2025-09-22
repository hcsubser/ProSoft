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
public class GetAllMesto extends AbstractSO{
    ArrayList<AbstractDomainObject> lista;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Mesto)) {
            throw new Exception("Prosledjeni objekat nije instanca trazene klase!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> objects = DBBroker.getInstance().select(ado);
        lista = objects;
    }
    
    public ArrayList<Mesto> vratiSvaMesta(){
        return (ArrayList<Mesto>)(Object)lista;
    }


}
