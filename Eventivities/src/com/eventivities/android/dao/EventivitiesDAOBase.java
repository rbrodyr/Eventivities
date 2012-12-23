package com.eventivities.android.dao;

import android.content.Context;

import com.eventivities.android.database.EventivitiesDatabase;
import com.j256.ormlite.dao.RuntimeExceptionDao;


public abstract class EventivitiesDAOBase<MainClass, ID> {

	protected final Context context;
	protected final EventivitiesDatabase eventivitiesDatabase;
	private RuntimeExceptionDao<MainClass, ID> dao;
	private Class<MainClass> clazzMainClass;

	EventivitiesDAOBase(Context context, Class<MainClass> clazzMainClass){
		this.context = context;
		this.eventivitiesDatabase = new EventivitiesDatabase(this.context);
		this.clazzMainClass = clazzMainClass;
	}
	
	public boolean isOpen(){
		return eventivitiesDatabase.isOpen();
	}

	public void close(){
		eventivitiesDatabase.close();
		dao = null;
	}
	
	protected RuntimeExceptionDao<MainClass, ID> getDAO() {
		if(dao == null)
			dao = eventivitiesDatabase.getRuntimeExceptionDao(clazzMainClass);
		
		return dao;
	}
}
