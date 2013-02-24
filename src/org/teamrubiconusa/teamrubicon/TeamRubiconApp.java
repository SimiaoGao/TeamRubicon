package org.teamrubiconusa.teamrubicon;

import org.teamrubiconusa.teamrubicon.dao.ActiveDao;
import org.teamrubiconusa.teamrubicon.dao.ItemDao;
import org.teamrubiconusa.teamrubicon.dao.PersonDao;
import org.teamrubiconusa.teamrubicon.dao.TypeDao;
import org.teamrubiconusa.teamrubicon.dao.WarehouseDao;

import android.app.Application;

public class TeamRubiconApp extends Application {
    @Override
    public void onCreate() {
    	WarehouseDao.getInstance().setContext(this.getApplicationContext());
    	TypeDao.getInstance().setContext(this.getApplicationContext());
    	ItemDao.getInstance().setContext(this.getApplicationContext());
    	PersonDao.getInstance().setContext(this.getApplicationContext());
    	ActiveDao.getInstance().setContext(this.getApplicationContext());
    }
}
