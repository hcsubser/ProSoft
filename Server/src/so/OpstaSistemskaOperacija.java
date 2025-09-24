/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbb.DatabaseBroker;
import domain.OpstiDomenskiObjekat;
import java.sql.*;
/**
 *
 * @author Saki
 */
public abstract class OpstaSistemskaOperacija {


    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;
    protected abstract void execute(OpstiDomenskiObjekat odo) throws Exception;

    public void templateExecute(OpstiDomenskiObjekat odo) throws Exception {
        try {
            validate(odo);
            execute(odo);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException, Exception {
        DatabaseBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException, Exception {
        DatabaseBroker.getInstance().getConnection().rollback();
    }

}
